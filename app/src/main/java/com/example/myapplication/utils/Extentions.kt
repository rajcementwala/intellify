package com.example.myapplication.utils

import com.example.myapplication.utils.Contstants.SERVER_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.getReadableDate(): String {
    val dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val readableDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm a")
    readableDateFormat.timeZone = TimeZone.getDefault()
    try {
       return readableDateFormat.format(dateFormat.parse(this))
    } catch (e: Exception) {
        return this
    }


}