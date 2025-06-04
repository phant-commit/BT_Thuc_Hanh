package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.* // No specific changes needed here unless removing individual remembers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.Screens
import com.example.myapplication.viewmodels.AuthViewModel // THÊM IMPORT NÀY

// SimpleEmailInputField không thay đổi, vẫn nhận email và onEmailChange
@Composable
fun SimpleEmailInputField(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Your Email") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController, authViewModel: AuthViewModel) { // THÊM authViewModel
    // KHÔNG CẦN state email cục bộ nữa, sẽ dùng từ authViewModel
    // var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                // Screen1 là màn hình đầu tiên, thường không có nút back ở đây
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "Illustration",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(100.dp)
            )

            Text(
                text = "UTH SmartTasks",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Forget Password?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enter your Email, we will send you a verification code.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email Input Field sử dụng authViewModel
            SimpleEmailInputField(
                email = authViewModel.email.value,
                onEmailChange = { authViewModel.email.value = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Screens.Screen2.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Screen1Preview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            // Để Preview hoạt động, bạn cần một AuthViewModel giả
            val dummyNavController = rememberNavController()
            val dummyAuthViewModel = AuthViewModel()
            Screen1(navController = dummyNavController, authViewModel = dummyAuthViewModel)
        }
    }
}