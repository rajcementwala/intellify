package com.example.myapplication.interfaces

import com.example.myapplication.models.TaskResponseItem

interface ItemSelectionListener {
    fun updateTask(task:TaskResponseItem)
    fun addRemoveFav(task:TaskResponseItem)
}