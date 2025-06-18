package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Lớp sealed cho trạng thái màn hình danh sách
sealed class UiState {
    object Loading : UiState()
    data class Success(val tasks: List<Task>) : UiState()
    data class Error(val message: String) : UiState()
}

// Lớp sealed riêng cho trạng thái màn hình chi tiết task
sealed class TaskDetailUiState {
    object Loading : TaskDetailUiState()
    data class Success(val task: Task) : TaskDetailUiState()
    data class Error(val message: String) : TaskDetailUiState()
}

class TaskViewModel : ViewModel() {
    // --- Trạng thái cho Danh sách Task (Screen6) ---
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    // --- Trạng thái cho Chi tiết Task (Screen7) ---
    private val _taskDetailState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val taskDetailState: StateFlow<TaskDetailUiState> = _taskDetailState

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccess) {
                    _uiState.value = UiState.Success(response.data)
                } else {
                    _uiState.value = UiState.Error(response.message)
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi gọi API danh sách: ${e.message}")
                _uiState.value = UiState.Error("Không thể lấy dữ liệu. Kiểm tra kết nối mạng.")
            }
        }
    }

    // Hàm để lấy một task theo ID
    fun fetchTaskById(taskId: Int) {
        _taskDetailState.value = TaskDetailUiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTaskDetail(taskId)
                if (response.isSuccess) {
                    _taskDetailState.value = TaskDetailUiState.Success(response.data)
                } else {
                    _taskDetailState.value = TaskDetailUiState.Error(response.message)
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi gọi API chi tiết: ${e.message}")
                _taskDetailState.value = TaskDetailUiState.Error("Không thể tải chi tiết task.")
            }
        }
    }
}