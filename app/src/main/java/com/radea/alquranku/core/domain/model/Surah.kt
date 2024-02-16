package com.radea.alquranku.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Surah(
    val surahNum: Int,
    val name: String,
    val latinName: String,
    val place: String,
    val totalAyat: Int,
    val arti: String,
    val description: String,
    val sourceAudio: String
) : Parcelable
