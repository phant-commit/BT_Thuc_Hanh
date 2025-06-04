package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.Screens // THÊM IMPORT NÀY NẾU CHƯA CÓ
import com.example.myapplication.viewmodels.AuthViewModel // THÊM IMPORT NÀY
import androidx.compose.material3.OutlinedTextFieldDefaults

// OtpInputField không thay đổi, vẫn nhận code và onCodeChange
@Composable
fun OtpInputField(
    codeLength: Int = 6,
    code: String,
    onCodeChange: (String) -> Unit
) {
    val focusRequesters = remember { List(codeLength) { FocusRequester() } }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (code.isEmpty()) {
            focusRequesters[0].requestFocus()
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        val chars = code.padEnd(codeLength, ' ').toCharArray()

        chars.forEachIndexed { index, c ->
            OutlinedTextField(
                value = if (c == ' ') "" else c.toString(),
                onValueChange = { value ->
                    if (value.length <= 1 && value.all { it.isDigit() }) {
                        val newChars = chars.copyOf()
                        newChars[index] = if (value.isEmpty()) ' ' else value[0]
                        val newCode = String(newChars).trimEnd()
                        onCodeChange(newCode)

                        if (value.isNotEmpty()) {
                            if (index < codeLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                focusManager.clearFocus()
                            }
                        } else {
                            if (index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .focusRequester(focusRequesters[index])
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color.Gray,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavController, authViewModel: AuthViewModel) { // THÊM authViewModel
    // KHÔNG CẦN state code cục bộ nữa
    // var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) { // SỬA: Dùng popBackStack()
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
                contentDescription = "Logo",
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

            // Bạn có thể hiển thị email ở đây nếu muốn, ví dụ:
            // Text(text = "Verification code sent to: ${authViewModel.email.value}")
            // Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter the code we just sent you on your registered Email",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OtpInputField(
                code = authViewModel.otp.value,
                onCodeChange = { authViewModel.otp.value = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Screens.Screen3.route) // SỬA: Dùng Screens.Screen3.route
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
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
fun Screen2Preview() {
    MaterialTheme {
        val dummyNavController = rememberNavController()
        val dummyAuthViewModel = AuthViewModel()
        // Có thể gán giá trị email giả cho ViewModel để xem trước
        // dummyAuthViewModel.email.value = "test@example.com"
        Screen2(navController = dummyNavController, authViewModel = dummyAuthViewModel)
    }
}