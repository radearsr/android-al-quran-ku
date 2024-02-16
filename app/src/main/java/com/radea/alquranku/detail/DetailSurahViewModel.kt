package com.radea.alquranku.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.radea.alquranku.core.domain.usecase.SurahUseCase

class DetailSurahViewModel(private val surahUseCase: SurahUseCase): ViewModel() {
    fun getAllAyat(surahNum: Int) = surahUseCase.getAllAyatBySurahNum(surahNum).asLiveData()
}