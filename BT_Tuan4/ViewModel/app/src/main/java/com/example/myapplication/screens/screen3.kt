package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.Screens
import com.example.myapplication.viewmodels.AuthViewModel // THÊM IMPORT NÀY
import androidx.compose.material3.OutlinedTextFieldDefaults // Đảm bảo import này có

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(navController: NavController, authViewModel: AuthViewModel) { // THÊM authViewModel
    // KHÔNG CẦN state password cục bộ nữa
    // var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") } // confirmPassword có thể giữ cục bộ

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
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
                contentDescription = "SmartTasks Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(100.dp)
            )

            Text(
                text = "SmartTasks",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Create new password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your new password must be different from previously used password",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = authViewModel.password.value, // SỬ DỤNG ViewModel
                onValueChange = { authViewModel.password.value = it }, // CẬP NHẬT ViewModel
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon")
                },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Confirm Password Icon")
                },
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (confirmPasswordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                // Thêm isError để kiểm tra mật khẩu khớp nhau nếu cần
                // isError = authViewModel.password.value.isNotEmpty() && confirmPassword.isNotEmpty() && authViewModel.password.value != confirmPassword
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Thêm logic kiểm tra mật khẩu ở đây nếu cần
                    // ví dụ: if (authViewModel.password.value == confirmPassword && authViewModel.password.value.isNotBlank())
                    navController.navigate(Screens.Screen4.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                // enabled = authViewModel.password.value == confirmPassword && authViewModel.password.value.isNotBlank() // Ví dụ vô hiệu hóa nút nếu mật khẩu không khớp
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Screen3Preview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val dummyNavController = rememberNavController()
            val dummyAuthViewModel = AuthViewModel()
            Screen3(navController = dummyNavController, authViewModel = dummyAuthViewModel)
        }
    }
}