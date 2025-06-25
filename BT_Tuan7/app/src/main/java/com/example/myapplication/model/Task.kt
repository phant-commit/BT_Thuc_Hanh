package com.example.myapplication.model

import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("category") val category: String,
    @SerializedName("dueDate") val dueDate: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("subtasks") val subtasks: List<Subtask>,
    @SerializedName("attachments") val attachments: List<Attachment>,
    @SerializedName("reminders") val reminders: List<Reminder>
)

data class Subtask(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean
)

data class Attachment(
    @SerializedName("id") val id: Int,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileUrl") val fileUrl: String
)

data class Reminder(
    @SerializedName("id") val id: Int,
    @SerializedName("time") val time: String,
    @SerializedName("type") val type: String
)

// Đổi tên bảng thành "task_table" cho đồng bộ
@Entity(tableName = "task_table")
// Đổi tên lớp thành Task
data class Homework(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "category_color")
    val categoryColor: Int,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)