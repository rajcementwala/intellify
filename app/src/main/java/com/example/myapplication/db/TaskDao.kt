package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.models.TaskResponseItem


@Dao
interface TaskDao {


    @Query("SELECT * FROM tasks")
    suspend fun getAllTask():List<TaskResponseItem>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskResponseItem: TaskResponseItem):Long
}