package com.radea.alquranku.schedule_sholat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.radea.alquranku.core.domain.model.City
import com.radea.alquranku.core.domain.model.DateItem
import com.radea.alquranku.core.domain.usecase.ScheduleSholatUseCase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleSholatViewModel(scheduleSholatUseCase: ScheduleSholatUseCase) : ViewModel() {
    private val _datesFromWeek = MutableLiveData<List<DateItem>>()
    val datesFromWeek: LiveData<List<DateItem>> = _datesFromWeek

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

    fun currentScheduleSholat() {
        val currentDate = _selectedFullDate.value
        val currentCityId = _selectedCityId.value
        Log.i("TEST", "$currentDate - $currentCityId")
    }
}