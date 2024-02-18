package com.radea.alquranku.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.radea.alquranku.core.data.source.local.entity.AyatEntity
import com.radea.alquranku.core.data.source.local.entity.CityEntity
import com.radea.alquranku.core.data.source.local.entity.ScheduleEntity
import com.radea.alquranku.core.data.source.local.entity.SurahEntity

@Database(entities = [SurahEntity::class, AyatEntity::class, CityEntity::class, ScheduleEntity::class], version = 1, exportSchema = false)
abstract class AlquranDatabase : RoomDatabase() {
    abstract fun alquranDao(): AlquranDao
}