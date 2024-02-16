package com.radea.alquranku.core.data.source.remote.response.surah

import com.google.gson.annotations.SerializedName

data class AyatItemResponse(
    @field:SerializedName("ar")
    val ar: String,

    @field:SerializedName("idn")
    val idn: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("surah")
    val surah: Int,

    @field:SerializedName("nomor")
    val nomor: Int,

    @field:SerializedName("tr")
    val tr: String
)
