package com.radea.alquranku.detail

import android.animation.LayoutTransition
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.radea.alquranku.R
import com.radea.alquranku.core.data.Resource
import com.radea.alquranku.core.data.ui.AyatAdapter
import com.radea.alquranku.core.domain.model.Surah
import com.radea.alquranku.databinding.ActivityDetailSurahBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class DetailSurahActivity : AppCompatActivity() {

    private val detailSurahViewModel: DetailSurahViewModel by viewModel()
    private lateinit var binding: ActivityDetailSurahBinding
    private lateinit var ayatAdapter: AyatAdapter
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        mediaPlayer = MediaPlayer()
        ayatAdapter = AyatAdapter()
        with(binding.rvListsAyat) {
            layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            setHasFixedSize(true)
            adapter = ayatAdapter
        }
        setContentView(binding.root)
        initObserver()
        val detailSurah = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DETAIL_SURAH, Surah::class.java)
        } else {
            intent.getParcelableExtra(DETAIL_SURAH)
        }
        if (detailSurah != null) {
            setDetailViews(detailSurah)
        }
        val iconPause = ContextCompat.getDrawable(this, R.drawable.baseline_pause_24)
        val iconPlay = ContextCompat.getDrawable(this, R.drawable.baseline_play_arrow_24)
        with(binding.fabPlaySurah) {
            setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    this.setImageDrawable(iconPlay)
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                } else {
                    this.setImageDrawable(iconPause)
                    playAudio()
                }
            }
        }
        with(binding) {
            expLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            mcvDescription.setOnClickListener {
                val currentView = if (tvSurahDescription.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                val currentRotation = if (tvSurahDescription.visibility == View.VISIBLE) 180f else 0f
                tvSurahDescription.visibility = currentView
                ivIconExp.rotation = currentRotation
            }
        }
    }

    private fun initObserver() {
        val surahNumber = intent.getIntExtra(SURAH_NUMBER, 0)
        detailSurahViewModel.getAllAyat(surahNumber).observe(this) { ayat ->
            if (ayat != null) {
                Log.i(TAG, "DETAIL SURAH $ayat")
                when (ayat) {
                    is Resource.Loading -> binding.llMainLoading.loadingLayout.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.llMainLoading.loadingLayout.visibility = View.GONE
                        ayatAdapter.setData(ayat.data)
                    }
                    is Resource.Error -> {
                        binding.llMainLoading.loadingLayout.visibility = View.GONE
                        Log.i(TAG, "LOGG ${ayat.message}")
                    }
                }
            }
        }
    }

    private fun setDetailViews(data: Surah) {
        AUDIO_URL = data.sourceAudio
        with(binding.cardBanner) {
            tvTitle.text = data.latinName
            tvSubTitle.text = data.arti
            tvInformation.text =
                resources.getString(R.string.jumlah_ayat, data.place, data.totalAyat)
                    .uppercase()
        }
        with(binding) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvSurahDescription.text = Html.fromHtml(data.description, Html.FROM_HTML_MODE_LEGACY)
            }
            toolbar.title = data.latinName
            toolbar.setNavigationOnClickListener {
                finish()
            }
            fabPlaySurah.visibility = View.VISIBLE
        }
    }

    private fun playAudio() {
        val attributes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        } else {
            null
        }
        try {
            mediaPlayer.setAudioAttributes(attributes)
            mediaPlayer.setDataSource(AUDIO_URL)
            mediaPlayer.prepare()
            mediaPlayer.setOnCompletionListener {
                val iconPlay = ContextCompat.getDrawable(this, R.drawable.baseline_play_arrow_24)
                binding.fabPlaySurah.setImageDrawable(iconPlay)
            }
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


    companion object {
        const val SURAH_NUMBER = "extra_surah_number"
        const val DETAIL_SURAH = "extra_detail_surah"
        private var AUDIO_URL: String = ""
        private val TAG = DetailSurahActivity::class.java.simpleName
    }
}