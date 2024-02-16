package com.radea.alquranku.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey
    @ColumnInfo(name = "surah_num")
    var surahNum: Int,

    @ColumnInfo(name = "latin_name")
    var latinName: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "place")
    var place: String,

    @ColumnInfo(name = "total_ayat")
    var totalAyat: Int,

    @ColumnInfo(name = "arti")
    var arti: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "source_audio")
    var sourceAudio: String
): Parcelable