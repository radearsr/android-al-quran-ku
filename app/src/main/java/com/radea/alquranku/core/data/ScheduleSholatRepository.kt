package com.radea.alquranku.core.data

import com.radea.alquranku.core.data.source.local.LocalDataSource
import com.radea.alquranku.core.data.source.remote.RemoteDataSource
import com.radea.alquranku.core.data.source.remote.network.ApiResponse
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ListCityResponse
import com.radea.alquranku.core.domain.model.City
import com.radea.alquranku.core.domain.repository.IScheduleSholatRepository
import com.radea.alquranku.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScheduleSholatRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IScheduleSholatRepository {
    override fun getAllCity(): Flow<Resource<List<City>>> =
        object : NetworkBoundResource<List<City>, ListCityResponse>() {
            override fun loadFromDB(): Flow<List<City>> {
                return localDataSource.getAllCity().map { DataMapper.mapCityEntityToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<ListCityResponse>> =
                remoteDataSource.getAllCity()

            override suspend fun saveCallResult(data: ListCityResponse) {
                val cityLists = DataMapper.mapCityResponseToEntity(data.data)
                localDataSource.insertCity(cityLists)
            }

            override fun shouldFetch(data: List<City>?): Boolean = data.isNullOrEmpty()
        }.asFlow()
}