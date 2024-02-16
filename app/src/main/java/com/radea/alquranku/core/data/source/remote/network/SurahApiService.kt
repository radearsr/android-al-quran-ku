package com.radea.alquranku.core.data.source.remote.network

import com.radea.alquranku.core.data.source.remote.response.surah.SurahDetailResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApiService {
    @GET("surah")
    suspend fun getListSurah(): List<SurahItemResponse>

    @GET("surah/{nomor}")
    suspend fun getDetailSurah(
        @Path("nomor") nomor: Int
    ): SurahDetailResponse
}