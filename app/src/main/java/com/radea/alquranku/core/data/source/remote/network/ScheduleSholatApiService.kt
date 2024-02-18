package com.radea.alquranku.core.data.source.remote.network

import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ListCityResponse
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleSholatApiService {
    @GET("sholat/kota/semua")
    suspend fun getAllCity(): ListCityResponse

    @GET("sholat/jadwal/{cityId}/{fullDate}")
    suspend fun getScheduleByDay(
        @Path("cityId") cityId: Int,
        @Path("fullDate") fullDate: String,
    ): ScheduleResponse

}