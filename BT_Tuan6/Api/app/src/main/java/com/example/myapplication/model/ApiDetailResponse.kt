package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

// Lớp này khớp với cấu trúc JSON có trường 'data' là một ĐỐI TƯỢNG Task duy nhất
data class ApiDetailResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("desImageURL")
    val desImageURL: String?, // Thêm trường này vì nó có trong JSON chi tiết

    @SerializedName("data")
    val data: Task // Quan trọng: data là một Task duy nhất
)