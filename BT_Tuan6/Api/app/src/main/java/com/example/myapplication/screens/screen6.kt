package com.example.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.model.Task
import com.example.myapplication.navigation.Screens
import com.example.myapplication.viewmodels.TaskViewModel
import com.example.myapplication.viewmodels.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen6(navController: NavHostController, taskViewModel: TaskViewModel) {
    val uiState by taskViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartTasks", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                // Bỏ nút back nếu đây là màn hình chính
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Điều hướng đến màn hình thêm task */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UiState.Success -> {
                    TaskList(tasks = state.tasks, navController = navController)
                }
                is UiState.Error -> {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, navController: NavHostController) {
    if (tasks.isEmpty()) {
        Text("No tasks found.")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(tasks) { task ->
                TaskItem(task = task, onTaskClicked = {
                    // Điều hướng tới Screen7 và truyền ID của task
                    navController.navigate(Screens.Screen7.createRoute(task.id))
                })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClicked: () -> Unit) {
    val backgroundColor = when (task.category) {
        "Work" -> Color(0xFFE8F5E9)
        "Fitness" -> Color(0xFFE1F5FE)
        "Personal" -> Color(0xFFFFF9C4)
        else -> Color(0xFFFCE4EC)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onTaskClicked),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, fontSize = 14.sp, color = Color.DarkGray, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Status: ${task.status}", fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}