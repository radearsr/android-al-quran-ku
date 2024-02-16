package com.radea.alquranku.core.domain.repository

import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface IScheduleSholatRepository {
    fun getAllCity(): Flow<Resource<List<City>>>
}