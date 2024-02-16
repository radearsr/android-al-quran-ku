package com.radea.alquranku.core.data.source.remote.response.surah

import com.google.gson.annotations.SerializedName
import com.radea.alquranku.core.data.source.remote.response.surah.AyatItemResponse

data class SurahDetailResponse(
    @field:SerializedName("nama")
	val nama: String? = null,

    @field:SerializedName("ayat")
	val ayat: List<AyatItemResponse>,

    @field:SerializedName("nama_latin")
	val namaLatin: String? = null,

    @field:SerializedName("jumlah_ayat")
	val jumlahAyat: Int? = null,

    @field:SerializedName("arti")
	val arti: String? = null,

    @field:SerializedName("tempat_turun")
	val tempatTurun: String? = null,

    @field:SerializedName("deskripsi")
	val deskripsi: String? = null,

    @field:SerializedName("audio")
	val audio: String? = null,

    @field:SerializedName("nomor")
	val nomor: Int? = null,

    @field:SerializedName("status")
	val status: Boolean? = null
)
