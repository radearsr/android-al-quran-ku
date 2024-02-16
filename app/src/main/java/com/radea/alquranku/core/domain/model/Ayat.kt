package com.radea.alquranku.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ayat(
    val surahNum: Int,
    val ayatNum: Int,
    val arabic: String,
    val latin: String,
    val arti: String
): Parcelable
