package com.radea.alquranku.di

import com.radea.alquranku.core.data.SurahRepository
import com.radea.alquranku.core.domain.usecase.ScheduleSholatInteractor
import com.radea.alquranku.core.domain.usecase.ScheduleSholatUseCase
import com.radea.alquranku.core.domain.usecase.SurahInteractor
import com.radea.alquranku.core.domain.usecase.SurahUseCase
import com.radea.alquranku.detail.DetailSurahViewModel
import com.radea.alquranku.home.HomeViewModel
import com.radea.alquranku.schedule_sholat.ScheduleSholatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SurahUseCase> { SurahInteractor(get()) }
    factory<ScheduleSholatUseCase> { ScheduleSholatInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailSurahViewModel(get()) }
    viewModel { ScheduleSholatViewModel(get()) }
}