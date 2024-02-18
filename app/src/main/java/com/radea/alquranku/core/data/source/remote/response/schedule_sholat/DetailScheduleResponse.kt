package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName

data class DetailScheduleResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("lokasi")
    val location: String,

    @field:SerializedName("daerah")
    val daerah: String,

    @field:SerializedName("jadwal")
    val jadwal: ScheduleDataItem
)