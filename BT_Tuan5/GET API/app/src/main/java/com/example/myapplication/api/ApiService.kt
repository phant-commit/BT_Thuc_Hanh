// ApiService.kt
package com.example.myapplication.api

import com.example.myapplication.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("product")
    suspend fun getProduct(): Product
}
