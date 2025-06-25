package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.TaskRepository
import com.example.myapplication.model.Task
import com.example.myapplication.model.toHomework
import com.example.myapplication.model.toTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class UiState {
    object Loading : UiState()
    data class Success(val tasks: List<Task>) : UiState()
    data class Error(val message: String) : UiState()
}

sealed class TaskDetailUiState {
    object Loading : TaskDetailUiState()
    data class Success(val task: Task) : TaskDetailUiState()
    data class Error(val message: String) : TaskDetailUiState()
}

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _taskDetailState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val taskDetailState: StateFlow<TaskDetailUiState> = _taskDetailState.asStateFlow()

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val tasks = repository.fetchTasksFromApi()
                _uiState.value = UiState.Success(tasks)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load tasks")
            }
        }
    }

    fun addTask(taskName: String, description: String) {
        viewModelScope.launch {
            val newTask = Task(
                id = 0,
                title = taskName,
                description = description,
                status = "New",
                priority = "Medium",
                category = "Personal",
                dueDate = "",
                createdAt = getCurrentTime(),
                updatedAt = getCurrentTime(),
                subtasks = emptyList(),
                attachments = emptyList(),
                reminders = emptyList()
            )

            try {
                val createdTask = repository.addTaskToApi(newTask)
                repository.insertHomework(createdTask.toHomework()) // Lưu local Room
                fetchTasks() // Refresh
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Add task failed")
            }
        }
    }

    fun fetchTaskById(taskId: Int) {
        viewModelScope.launch {
            _taskDetailState.value = TaskDetailUiState.Loading
            try {
                val homeworkList = repository.getAllHomework()
                val homework = homeworkList.find { it.id == taskId }
                if (homework != null) {
                    _taskDetailState.value = TaskDetailUiState.Success(homework.toTask())
                } else {
                    _taskDetailState.value = TaskDetailUiState.Error("Task not found")
                }
            } catch (e: Exception) {
                _taskDetailState.value = TaskDetailUiState.Error(e.message ?: "Error fetching task")
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteTaskFromApi(task.id)
                repository.deleteHomework(task.toHomework())
                fetchTasks()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Delete failed")
            }
        }
    }
}
