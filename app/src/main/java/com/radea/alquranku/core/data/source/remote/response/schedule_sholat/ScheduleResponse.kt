package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName


data class ScheduleResponse(
    @field:SerializedName("request")
    val request: RequestResponse,

    @field:SerializedName("data")
    val data: DetailScheduleResponse,

    @field:SerializedName("status")
    val status: Boolean
)
