package com.example.myapplication.models

data class TaskUpdateRequest(
    val task_id:Int,
    val task_name:String,
    val task_details:String,
    val is_favourite:Boolean
)