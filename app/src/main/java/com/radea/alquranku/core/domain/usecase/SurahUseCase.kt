package com.radea.alquranku.core.domain.usecase

import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.domain.model.Ayat
import com.radea.alquranku.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface SurahUseCase {
    fun getAllSurah(): Flow<Resource<List<Surah>>>
    fun getAllAyatBySurahNum(surahNum: Int): Flow<Resource<List<Ayat>>>
}