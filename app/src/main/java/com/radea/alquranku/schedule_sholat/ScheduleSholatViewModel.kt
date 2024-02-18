package com.radea.alquranku.schedule_sholat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.domain.model.City
import com.radea.alquranku.core.domain.model.DateItem
import com.radea.alquranku.core.domain.model.ScheduleSholat
import com.radea.alquranku.core.domain.usecase.ScheduleSholatUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleSholatViewModel(private val scheduleSholatUseCase: ScheduleSholatUseCase) :
    ViewModel() {
    private val _datesFromWeek = MutableLiveData<List<DateItem>>()
    val datesFromWeek: LiveData<List<DateItem>> = _datesFromWeek

    private val _listSchedule = MutableLiveData<List<ScheduleSholat>>()
    val listSchedule: LiveData<List<ScheduleSholat>> = _listSchedule

    private val _isLoadingSchedule = MutableLiveData<Boolean>()
    val isLoadingSchedule: LiveData<Boolean> = _isLoadingSchedule

    private val _errorMessageSchedule = MutableLiveData<String>()
    val errorMessageSchedule: LiveData<String> = _errorMessageSchedule

    private val _selectedFullDate = MutableLiveData<String>()
    private val _selectedCityId = MutableLiveData<Int>()
    private val _listCities = MutableLiveData<List<City>>()

    val cities = scheduleSholatUseCase.getAllCity().asLiveData()

    fun generateDateList() {
        val dateList = mutableListOf<DateItem>()
        val calendar = Calendar.getInstance()

        for (index in 0 until 7) {
            val selectedDate = index == 0
            val dayOfWeek = SimpleDateFormat("EEE", Locale("id", "ID")).format(calendar.time)
            val fullDate = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID")).format(calendar.time)
            dateList.add(
                DateItem(
                    calendar.get(Calendar.DAY_OF_MONTH),
                    dayOfWeek,
                    fullDate,
                    selectedDate
                )
            )
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        _datesFromWeek.value = dateList
    }

    fun updateSelectedDate(date: DateItem) {
        val newDatesList = ArrayList<DateItem>()
        val datesList = _datesFromWeek.value
        datesList?.forEach {
            if (it == date) {
                newDatesList.add(it.copy(isSelected = true))
            } else {
                newDatesList.add(it.copy(isSelected = false))
            }
        }
        _datesFromWeek.value = newDatesList
    }

    fun setListsCity(listCity: List<City>) {
        _listCities.value = listCity
    }

    fun setSelectedFullDate(fullDate: String) {
        _selectedFullDate.value = fullDate
    }

    fun setSelectedCity(name: String) {
        _listCities.value?.forEach { city ->
            if (city.name == name) {
                _selectedCityId.value = city.id
            }
        }
    }

    fun getSelectedFullDate(): String? {
        return _selectedFullDate.value
    }

    fun getSelectedCity(): Int? {
        return _selectedCityId.value
    }

    fun getScheduleByDate(cityId: Int, fullDate: String) {
        viewModelScope.launch {
            try {
                scheduleSholatUseCase.getScheduleByDate(cityId, fullDate).collect {
                    when(it) {
                        is Resource.Loading -> {
                            _isLoadingSchedule.value = true
                        }
                        is Resource.Success -> {
                            _isLoadingSchedule.value = false
                            _listSchedule.value = it.data as List<ScheduleSholat>
                        }
                        is Resource.Error -> {
                            _isLoadingSchedule.value = false
                            _errorMessageSchedule.value = it.message.toString()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ScheduleSholatViewModel", "Message $e")
            }
        }
    }
}