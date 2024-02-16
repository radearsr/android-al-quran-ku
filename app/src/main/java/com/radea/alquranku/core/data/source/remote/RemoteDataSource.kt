package com.radea.alquranku.core.data.source.remote

import android.util.Log
import com.radea.alquranku.core.data.source.remote.network.ApiResponse
import com.radea.alquranku.core.data.source.remote.network.ScheduleSholatApiService
import com.radea.alquranku.core.data.source.remote.network.SurahApiService
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ListCityResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahDetailResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val surahApiService: SurahApiService,
    private val scheduleSholatApiService: ScheduleSholatApiService
) {
    fun getAllSurah(): Flow<ApiResponse<List<SurahItemResponse>>> {
        return flow {
            try {
                val response = surahApiService.getListSurah()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailSurah(surahId: Int): Flow<ApiResponse<SurahDetailResponse>> {
        return flow {
            try {
                val response = surahApiService.getDetailSurah(surahId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllCity(): Flow<ApiResponse<ListCityResponse>> {
        return flow {
            try {
                val response = scheduleSholatApiService.getAllCity()
                if (response.data.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}