package com.radea.alquranku.core.data.source.local

import com.radea.alquranku.core.data.source.local.entity.AyatEntity
import com.radea.alquranku.core.data.source.local.entity.CityEntity
import com.radea.alquranku.core.data.source.local.entity.SurahEntity
import com.radea.alquranku.core.data.source.local.room.AlquranDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val alquranDao: AlquranDao) {
    fun getAllSurah(): Flow<List<SurahEntity>> = alquranDao.getAllSurah()

    suspend fun insertSurah(surahList: List<SurahEntity>) = alquranDao.insertSurah(surahList)

    fun getAllAyatBySurahNum(surahNum: Int) = alquranDao.getAllAyatBySurahNum(surahNum)

    suspend fun insertAyat(ayatList: List<AyatEntity>) = alquranDao.insertAyat(ayatList)

    fun getAllCity() = alquranDao.getAllCity()

    suspend fun insertCity(cityList: List<CityEntity>) = alquranDao.insertCity(cityList)
}