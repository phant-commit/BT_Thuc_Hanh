package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R

@Composable
fun Screen4(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo UTH (thay bằng tên ảnh đúng của bạn nếu khác)
            Image(
                painter = painterResource(id = R.drawable.uth), // Đặt tên ảnh logo là logo_uth.png trong thư mục res/drawable
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .width(180 * 1.5.dp)
                    .height(120 * 1.5.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tên app
            Text(
                text = "UTH SmartTasks",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3), // Màu xanh dương
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen4Preview() {
    Screen4(navController = rememberNavController())
}
