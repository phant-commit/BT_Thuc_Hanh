// screen8.kt
package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// MỚI: Đổi tên composable để nhất quán
@Composable
fun Screen8(
    onBackClick: () -> Unit,
    onAddClick: (String, String) -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }
            Text(
                text = "Add New",
                fontSize = 22.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Task Input
        Text(text = "Task", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your task") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description Input
        Text(text = "Description", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { Text("Enter description") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Add Button
        Button(
            onClick = {
                // Thêm một kiểm tra đơn giản để đảm bảo tên task không trống
                if (taskName.isNotBlank()) {
                    onAddClick(taskName, description)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .height(50.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            // Vô hiệu hóa nút nếu tên task trống
            enabled = taskName.isNotBlank()
        ) {
            Text(text = "Add", fontSize = 16.sp, color = Color.White)
        }
    }
}