package com.radea.alquranku.core.data.source.remote.network

import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ListCityResponse
import retrofit2.http.GET

interface ScheduleSholatApiService {
    @GET("sholat/kota/semua")
    suspend fun getAllCity(): ListCityResponse
}