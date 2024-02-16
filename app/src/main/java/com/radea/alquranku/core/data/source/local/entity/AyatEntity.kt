package com.radea.alquranku.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ayat")
data class AyatEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "surah_num")
    var surahNum: Int,

    @ColumnInfo(name = "ayat_num")
    var ayatNum: Int,

    @ColumnInfo(name = "arabic")
    var arabic: String,

    @ColumnInfo(name = "latin")
    var latin: String,

    @ColumnInfo(name = "arti")
    var arti: String,
): Parcelable
