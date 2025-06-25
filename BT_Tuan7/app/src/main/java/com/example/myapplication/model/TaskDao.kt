package com.example.myapplication.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homework: Homework): Long

    @Delete
    suspend fun delete(homework: Homework)

    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getHomeworkById(id: Int): Homework?

    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllHomework(): Flow<List<Homework>>
}
