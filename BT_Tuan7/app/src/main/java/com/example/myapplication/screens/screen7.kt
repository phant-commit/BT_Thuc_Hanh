package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.model.Attachment
import com.example.myapplication.model.Subtask
import com.example.myapplication.model.Task
import com.example.myapplication.viewmodels.TaskDetailUiState
import com.example.myapplication.viewmodels.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen7(navController: NavHostController, taskViewModel: TaskViewModel, taskId: Int) {
    LaunchedEffect(key1 = taskId) {
        taskViewModel.fetchTaskById(taskId)
    }

    val uiState by taskViewModel.taskDetailState.collectAsState()
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    val currentTask = (uiState as? TaskDetailUiState.Success)?.task

    if (showDeleteConfirmationDialog && currentTask != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete this task: \"${currentTask.title}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        taskViewModel.deleteTask(currentTask)
                        showDeleteConfirmationDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Delete", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (currentTask != null) {
                            showDeleteConfirmationDialog = true
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is TaskDetailUiState.Loading -> CircularProgressIndicator()
                is TaskDetailUiState.Success -> TaskDetailContent(task = state.task)
                is TaskDetailUiState.Error -> Text(
                    text = state.message,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TaskDetailContent(task: Task) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(task.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(task.description, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    InfoColumn("Category", task.category, painterResource(id = R.drawable.ic_category))
                    InfoColumn("Status", task.status, painterResource(id = R.drawable.ic_status))
                    InfoColumn("Priority", task.priority, painterResource(id = R.drawable.ic_priority))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        if (task.subtasks.isNotEmpty()) {
            item {
                Text("Subtasks", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(task.subtasks.size) { index ->
                SubtaskItem(subtask = task.subtasks[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if (task.attachments.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Attachments", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(task.attachments.size) { index ->
                AttachmentItem(attachment = task.attachments[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun InfoColumn(title: String, value: String, icon: Painter, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(painter = icon, contentDescription = title, modifier = Modifier.size(28.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Checkbox(
            checked = subtask.isCompleted,
            onCheckedChange = { /* TODO */ }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(subtask.title, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { /* TODO */ }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_attachment), contentDescription = "Attachment", modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(attachment.fileName, style = MaterialTheme.typography.bodyLarge)
    }
}
