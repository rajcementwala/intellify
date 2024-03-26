package com.example.myapplication.repository

import com.example.myapplication.models.TaskResponseItem

interface SyncRepository {
    suspend fun insertTask(task:TaskResponseItem)
}