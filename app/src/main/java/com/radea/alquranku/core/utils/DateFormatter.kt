package com.radea.alquranku.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    fun getCurrentFormattedDate(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return formatter.format(currentDate)
    }

    fun getCurrentDay(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("EEEE", Locale("id", "ID"))
        return formatter.format(currentDate)
    }
}