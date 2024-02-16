package com.radea.alquranku.core.domain.model

data class DateItem(
    val date: Int,
    val day: String,
    val fullDate: String,
    var isSelected: Boolean = false
)
