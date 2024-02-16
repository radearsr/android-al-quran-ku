package com.radea.alquranku.core.data.source.remote.response.surah

import com.google.gson.annotations.SerializedName

class SurahItemResponse(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("nama_latin")
    val namaLatin: String,

    @field:SerializedName("jumlah_ayat")
    val jumlahAyat: Int,

    @field:SerializedName("tempat_turun")
    val tempatTurun: String,

    @field:SerializedName("arti")
    val arti: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("audio")
    val audio: String,

    @field:SerializedName("nomor")
    val nomor: Int
)