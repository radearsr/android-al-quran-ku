package com.radea.alquranku.core.data

import com.radea.alquranku.core.data.source.local.LocalDataSource
import com.radea.alquranku.core.data.source.remote.RemoteDataSource
import com.radea.alquranku.core.data.source.remote.network.ApiResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahDetailResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahItemResponse
import com.radea.alquranku.core.domain.model.Ayat
import com.radea.alquranku.core.domain.model.Surah
import com.radea.alquranku.core.domain.repository.ISurahRepository
import com.radea.alquranku.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SurahRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ISurahRepository {
    override fun getAllSurah(): Flow<Resource<List<Surah>>> = object : NetworkBoundResource<List<Surah>, List<SurahItemResponse>>() {
        override fun loadFromDB(): Flow<List<Surah>> {
            return localDataSource.getAllSurah().map { DataMapper.mapEntitiesToDomain(it) }
        }

        override suspend fun createCall(): Flow<ApiResponse<List<SurahItemResponse>>> = remoteDataSource.getAllSurah()

        override suspend fun saveCallResult(data: List<SurahItemResponse>) {
            val surahList = DataMapper.mapResponseToEntity(data)
            localDataSource.insertSurah(surahList)
        }

        override fun shouldFetch(data: List<Surah>?): Boolean = data.isNullOrEmpty()
    }.asFlow()

    override fun getAllAyatBySurahNum(surahNum: Int): Flow<Resource<List<Ayat>>> = object : NetworkBoundResource<List<Ayat>, SurahDetailResponse>() {
        override fun loadFromDB(): Flow<List<Ayat>> {
            return localDataSource.getAllAyatBySurahNum(surahNum).map { DataMapper.mapAyatEntityToDomain(it) }
        }

        override suspend fun createCall(): Flow<ApiResponse<SurahDetailResponse>> = remoteDataSource.getDetailSurah(surahNum)

        override suspend fun saveCallResult(data: SurahDetailResponse) {
            val ayatList = DataMapper.mapAyatResponseToEntity(data.ayat)
            localDataSource.insertAyat(ayatList)
        }

        override fun shouldFetch(data: List<Ayat>?): Boolean = data.isNullOrEmpty()
    }.asFlow()
}