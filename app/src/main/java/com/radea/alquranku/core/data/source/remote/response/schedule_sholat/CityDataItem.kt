package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName

data class CityDataItem(
    @field:SerializedName("lokasi")
    val lokasi: String,

    @field:SerializedName("id")
    val id: String
)
