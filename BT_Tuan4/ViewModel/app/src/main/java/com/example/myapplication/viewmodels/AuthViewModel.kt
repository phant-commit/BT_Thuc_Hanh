package com.example.myapplication.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    val email = mutableStateOf("")
    val otp = mutableStateOf("")
    val password = mutableStateOf("") // Mật khẩu mới từ Screen3
}