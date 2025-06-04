package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home // Import Home icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.Screens
import com.example.myapplication.navigation.SetupNavGraph
import com.example.myapplication.screens.Screen1
import com.example.myapplication.screens.Screen2
import com.example.myapplication.screens.Screen3
import com.example.myapplication.screens.Screen4
import com.example.myapplication.viewmodels.AuthViewModel
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.Screen1
import com.example.myapplication.screens.Screen2
import com.example.myapplication.screens.Screen3
import com.example.myapplication.screens.Screen4



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Sử dụng AppTheme của bạn
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    // Khởi tạo AuthViewModel sẽ được chia sẻ
                    val authViewModel: AuthViewModel = viewModel()

                    NavHost(navController = navController, startDestination = Screens.Screen1.route) {
                        composable(Screens.Screen1.route) {
                            Screen1(navController = navController, authViewModel = authViewModel)
                        }
                        composable(Screens.Screen2.route) {
                            Screen2(navController = navController, authViewModel = authViewModel)
                        }
                        composable(Screens.Screen3.route) {
                            Screen3(navController = navController, authViewModel = authViewModel)
                        }
                        composable(Screens.Screen4.route) {
                            Screen4(navController = navController, authViewModel = authViewModel)
                        }
                        // Thêm các composable khác nếu có
                    }
                }
            }
        }
    }
}