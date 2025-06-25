package com.example.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Sửa lại baseurl, chỉ chứa phần gốc của API
    private const val BASE_URL = "https://amock.io/api/researchUTH/"

    val api: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // <-- Sử dụng baseUrl đã sửa
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }
}