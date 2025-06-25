package com.example.myapplication.api

import com.example.myapplication.model.ApiDetailResponse
import com.example.myapplication.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiService {
    // Hàm này dùng ApiResponse để lấy danh sách task
    @GET("tasks")
    suspend fun getTasks(): ApiResponse

    // Hàm này dùng ApiDetailResponse để lấy chi tiết task
    // Sửa lại tên tham số cho khớp với convention: "taskId"
    @GET("task/{taskId}")
    suspend fun getTaskDetail(@Path("taskId") taskId: Int): ApiDetailResponse
}