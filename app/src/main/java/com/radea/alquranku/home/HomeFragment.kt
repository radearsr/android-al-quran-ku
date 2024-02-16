package com.radea.alquranku.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.radea.alquranku.R
import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.data.ui.SurahAdapter
import com.radea.alquranku.core.utils.DateFormatter
import com.radea.alquranku.databinding.FragmentHomeBinding
import com.radea.alquranku.detail.DetailSurahActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val surahAdapter = SurahAdapter()
        surahAdapter.onItemClick = {selectedData ->
            val intent = Intent(activity, DetailSurahActivity::class.java)
            intent.putExtra(DetailSurahActivity.SURAH_NUMBER, selectedData.surahNum)
            intent.putExtra(DetailSurahActivity.DETAIL_SURAH, selectedData)
            startActivity(intent)
        }
        binding?.tvWelcomeUser?.text = getString(R.string.current_username)
        binding?.cardBanner?.tvSubTitle?.text = DateFormatter.getCurrentFormattedDate()
        binding?.cardBanner?.tvTitle?.text = DateFormatter.getCurrentDay()
        binding?.cardBanner?.tvInformation?.text = resources.getString(R.string.current_location)
        homeViewModel.surah.observe(viewLifecycleOwner) { surah ->
            if (surah != null) {
                when(surah) {
                    is Resource.Loading -> binding?.llMainLoading?.loadingLayout?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.llMainLoading?.loadingLayout?.visibility = View.GONE
                        surahAdapter.setData(surah.data)
                    }
                    is Resource.Error -> {
                        binding?.llMainLoading?.loadingLayout?.visibility = View.GONE
                        Log.i(TAG, "LOGG ${surah.message}")
                    }
                }
            }
        }
        with(binding?.rvDaftarSurah) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = surahAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }
}