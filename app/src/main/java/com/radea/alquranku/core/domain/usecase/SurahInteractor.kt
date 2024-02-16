package com.radea.alquranku.core.domain.usecase

import com.radea.alquranku.core.domain.repository.ISurahRepository

class SurahInteractor(private val surahRepository: ISurahRepository): SurahUseCase {
    override fun getAllSurah() = surahRepository.getAllSurah()
    override fun getAllAyatBySurahNum(surahNum: Int) = surahRepository.getAllAyatBySurahNum(surahNum)
}