package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName

data class RequestResponse(
    @field:SerializedName("path")
    val path: String
)
