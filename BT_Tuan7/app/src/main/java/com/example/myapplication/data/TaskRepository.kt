package com.example.myapplication.data

import com.example.myapplication.model.*
import com.google.firebase.appdistribution.gradle.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class TaskRepository(
    private val apiService: ApiService,
    private val taskDao: TaskDao
) {
    suspend fun fetchTasksFromApi(): List<Task> = apiService.getTasks().data
    suspend fun addTaskToApi(task: Task): Task = apiService.addTask(task).data
    suspend fun deleteTaskFromApi(id: Int) = apiService.deleteTask(id)

    suspend fun insertHomework(homework: Homework): Long = taskDao.insert(homework)
    suspend fun deleteHomework(homework: Homework) = taskDao.delete(homework)

    suspend fun getHomeworkById(id: Int): Homework? = taskDao.getHomeworkById(id)
    fun getAllHomework(): Flow<List<Homework>> = taskDao.getAllHomework()
}
