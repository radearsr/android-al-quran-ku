package com.radea.alquranku.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.radea.alquranku.core.domain.usecase.SurahUseCase

class HomeViewModel(surahUseCase: SurahUseCase): ViewModel() {
    val surah = surahUseCase.getAllSurah().asLiveData()
}