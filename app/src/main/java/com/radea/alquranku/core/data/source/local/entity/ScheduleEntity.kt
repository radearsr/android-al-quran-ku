package com.radea.alquranku.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "city_id")
    var cityId: Int,


    @ColumnInfo(name = "full_date")
    var fullDate: String,


    @ColumnInfo(name = "key")
    var key: String,


    @ColumnInfo(name = "value")
    var value: String,

    ): Parcelable
