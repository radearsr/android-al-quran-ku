package com.radea.alquranku.schedule_sholat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.radea.alquranku.R
import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.data.ui.DateScheduleAdapter
import com.radea.alquranku.core.domain.model.City
import com.radea.alquranku.databinding.FragmentScheduleSholatBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleSholatFragment : Fragment() {
    private val scheduleSholatViewModel: ScheduleSholatViewModel by viewModel()
    private lateinit var binding: FragmentScheduleSholatBinding
    private lateinit var dateScheduleAdapter: DateScheduleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleSholatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dropdownArrayAdapter =
            ArrayAdapter<String>(requireContext(), R.layout.item_dropdown_city)
        scheduleSholatViewModel.generateDateList()
        val calendar = Calendar.getInstance()

        dateScheduleAdapter = DateScheduleAdapter()
        with(binding) {
            autoCompleteTv.setAdapter(dropdownArrayAdapter)
            autoCompleteTv.threshold = 1
            rvDateSchedule.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvDateSchedule.adapter = dateScheduleAdapter
            tvMonthYear.text =
                SimpleDateFormat("MMMM yyyy", Locale("id", "ID")).format(calendar.time)
        }


        binding.autoCompleteTv.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Log.i(TAG, "SELECTED DROPDOWN $selectedItem")
            scheduleSholatViewModel.setSelectedCity(selectedItem)
            scheduleSholatViewModel.currentScheduleSholat()
        }

        scheduleSholatViewModel.cities.observe(viewLifecycleOwner) { cities ->
            when (cities) {
                is Resource.Loading -> {
                    Log.i(TAG, "Get City Loading")
                }

                is Resource.Success -> {
                    val cityNames = mutableListOf<String>()
                    cities.data?.map { cityNames.add(it.name) }
                    cities.data?.let { scheduleSholatViewModel.setListsCity(it) }
                    dropdownArrayAdapter.addAll(cityNames)
                    dropdownArrayAdapter.notifyDataSetChanged()
                    scheduleSholatViewModel.setSelectedCity("KOTA SEMARANG")
                    scheduleSholatViewModel.currentScheduleSholat()
                }

                is Resource.Error -> {
                    Log.e(TAG, "Error Get City ${cities.message}")
                }
            }
        }

        scheduleSholatViewModel.datesFromWeek.observe(viewLifecycleOwner) { dates ->
            dateScheduleAdapter.setData(dates)
            val selectedDate = dates.firstOrNull { it.isSelected }
            selectedDate?.let { scheduleSholatViewModel.setSelectedFullDate(it.fullDate)  }
            scheduleSholatViewModel.currentScheduleSholat()
        }

        dateScheduleAdapter.onItemClick = { date ->
            scheduleSholatViewModel.updateSelectedDate(date)
        }

    }


    companion object {
        private val TAG = ScheduleSholatFragment::class.java.simpleName
    }
}