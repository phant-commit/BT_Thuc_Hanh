package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

// Lớp này khớp với cấu trúc JSON bên ngoài
data class ApiResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    @SerializedName("message")
    val message: String,

    // Quan trọng nhất: trường 'data' chứa danh sách các Task
    @SerializedName("data")
    val data: List<Task>
)
