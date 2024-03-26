package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.TaskResponseItem


@Database(entities = [TaskResponseItem::class], version = 1)
abstract class TaskDatabase:RoomDatabase() {
        abstract fun taskDao():TaskDao

}