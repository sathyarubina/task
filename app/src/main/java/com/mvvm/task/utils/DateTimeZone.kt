package com.mvvm.task.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateTimeZone {
    companion object DateTimeZone {
        @RequiresApi(Build.VERSION_CODES.O)
        fun DateTimeZone(TimeUTCinSeconds: Long): String {
            val dateTime: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(TimeUTCinSeconds),
                TimeZone.getDefault().toZoneId()
            )
            val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            return format.format(dateTime)
        }
    }
}