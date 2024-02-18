package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName

data class ScheduleDataItem(
    @field:SerializedName("tanggal")
    val tanggal: String,

    @field:SerializedName("imsak")
    val imsak: String,

    @field:SerializedName("subuh")
    val subuh: String,

    @field:SerializedName("terbit")
    val terbit: String,

    @field:SerializedName("dhuha")
    val dhuha: String,

    @field:SerializedName("dzuhur")
    val dzuhur: String,

    @field:SerializedName("ashar")
    val ashar: String,

    @field:SerializedName("maghrib")
    val magrib: String,

    @field:SerializedName("isya")
    val isya: String,

    @field:SerializedName("date")
    val date: String,
)