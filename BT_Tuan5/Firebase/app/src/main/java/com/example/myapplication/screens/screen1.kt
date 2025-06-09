package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun Screen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Hàng ngang chứa chấm và nút skip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dấu chấm tròn (hiển thị màn đầu tiên)
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    val color = if (index == 0) Color(0xFF2196F3) else Color.LightGray
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(color, shape = CircleShape)
                    )
                }
            }

            // Nút skip
            TextButton(onClick = { navController.navigate(Screens.Screen4.route) }) {
                Text("Skip", color = Color(0xFF2196F3))
            }
        }

        // Nội dung chính
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.screen2), // Đổi thành ảnh Screen1 phù hợp
                contentDescription = "Illustration",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(240.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome to Our App",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your productivity starts here. Let's explore how to manage your time effectively and increase your focus.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        // Nút Next (không có nút Back)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại hình tròn


            // Nút Next
            Button(
                onClick = { navController.navigate(Screens.Screen2.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3) // Màu nền của nút
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f)

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
            Screen1(navController = rememberNavController())
        }
    }
}
