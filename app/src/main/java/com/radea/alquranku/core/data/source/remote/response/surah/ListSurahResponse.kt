package com.radea.alquranku.core.data.source.remote.response.surah

import com.google.gson.annotations.SerializedName

data class ListSurahResponse(
    @field:SerializedName("Response")
    val listSurah: List<SurahItemResponse>
)
