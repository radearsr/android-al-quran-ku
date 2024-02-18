package com.radea.alquranku.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radea.alquranku.core.data.source.local.entity.AyatEntity
import com.radea.alquranku.core.data.source.local.entity.CityEntity
import com.radea.alquranku.core.data.source.local.entity.ScheduleEntity
import com.radea.alquranku.core.data.source.local.entity.SurahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlquranDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurah(surah: List<SurahEntity>)

    @Query("SELECT * FROM surah")
    fun getAllSurah(): Flow<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyat(ayat: List<AyatEntity>)

    @Query("SELECT * FROM ayat WHERE surah_num = :surahNum")
    fun getAllAyatBySurahNum(surahNum: Int): Flow<List<AyatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: List<CityEntity>)

    @Query("SELECT * FROM city")
    fun getAllCity(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: List<ScheduleEntity>)

    @Query("SELECT * FROM schedule WHERE city_id = :cityId AND full_date = :fullDate ORDER BY value ASC")
    fun getScheduleByDate(cityId: Int, fullDate: String): Flow<List<ScheduleEntity>>
}