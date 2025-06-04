package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.* // Không cần thay đổi ở đây, chỉ là các state từ ViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.viewmodels.AuthViewModel // THÊM IMPORT NÀY
import androidx.compose.material3.OutlinedTextFieldDefaults
// import com.example.myapplication.navigation.Screens // Import này không cần thiết ở đây trừ khi bạn điều hướng từ Submit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen4(navController: NavController, authViewModel: AuthViewModel) { // THÊM authViewModel
    // KHÔNG CẦN state cục bộ cho email, code, password
    // var email by remember { mutableStateOf("") }
    // var code by remember { mutableStateOf("") }
    // var password by remember { mutableStateOf("") }

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
                contentDescription = "UTH Logo",
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
                text = "Confirm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Review your information.", // Thay đổi văn bản cho phù hợp
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Hiển thị Email từ ViewModel
            OutlinedTextField(
                value = authViewModel.email.value,
                onValueChange = { authViewModel.email.value = it }, // Cho phép chỉnh sửa nếu muốn
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                // readOnly = true // Bỏ comment nếu chỉ muốn hiển thị, không cho sửa
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị Code (OTP) từ ViewModel
            OutlinedTextField(
                value = authViewModel.otp.value,
                onValueChange = { authViewModel.otp.value = it }, // Cho phép chỉnh sửa nếu muốn
                label = { Text("Code") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Envelope Icon") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                // readOnly = true // Bỏ comment nếu chỉ muốn hiển thị
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị Password từ ViewModel
            OutlinedTextField(
                value = authViewModel.password.value,
                onValueChange = { authViewModel.password.value = it }, // Cho phép chỉnh sửa nếu muốn
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
                visualTransformation = PasswordVisualTransformation(), // Có thể thêm nút show/hide nếu cần
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                // readOnly = true // Bỏ comment nếu chỉ muốn hiển thị
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Dữ liệu đã có trong authViewModel.
                    // Khi nhấn Submit, dữ liệu này coi như là "chốt".
                    // Nếu người dùng quay lại Screen1, 2, 3, họ sẽ thấy dữ liệu này.
                    // Tại đây bạn có thể thực hiện logic submit cuối cùng (ví dụ: gửi lên server)
                    // và sau đó có thể điều hướng đến một màn hình thành công hoặc màn hình chính.
                    // Ví dụ: navController.navigate("home_screen_route") { popUpTo(Screens.Screen1.route) { inclusive = true } }
                    println("Submit clicked!")
                    println("Email: ${authViewModel.email.value}")
                    println("OTP: ${authViewModel.otp.value}")
                    println("Password: ${authViewModel.password.value}")
                    // Để minh họa việc "đổ ngược" dữ liệu khi quay lại:
                    // Không cần làm gì thêm, vì các màn hình trước đó đã đọc từ ViewModel.
                    // Nếu bạn muốn người dùng không thể quay lại các màn hình 1,2,3 sau khi submit thành công,
                    // bạn sẽ dùng popUpTo để xóa backstack.
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Submit")
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Screen4Preview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val dummyNavController = rememberNavController()
            val dummyAuthViewModel = AuthViewModel().apply {
                email.value = "preview@example.com"
                otp.value = "123456"
                password.value = "password123"
            }
            Screen4(navController = dummyNavController, authViewModel = dummyAuthViewModel)
        }
    }
}