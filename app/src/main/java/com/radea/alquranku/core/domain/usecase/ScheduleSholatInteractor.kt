package com.radea.alquranku.core.domain.usecase

import com.radea.alquranku.core.domain.repository.IScheduleSholatRepository

class ScheduleSholatInteractor(private val scheduleSholatUseCase: IScheduleSholatRepository): ScheduleSholatUseCase {
    override fun getAllCity() = scheduleSholatUseCase.getAllCity()
    override fun getScheduleByDate(
        cityId: Int,
        fullDate: String
    ) = scheduleSholatUseCase.getScheduleByDate(cityId, fullDate)
}