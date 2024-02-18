package com.radea.alquranku.core.utils

import com.radea.alquranku.core.data.source.local.entity.AyatEntity
import com.radea.alquranku.core.data.source.local.entity.CityEntity
import com.radea.alquranku.core.data.source.local.entity.ScheduleEntity
import com.radea.alquranku.core.data.source.local.entity.SurahEntity
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.CityDataItem
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.DetailScheduleResponse
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ScheduleDataItem
import com.radea.alquranku.core.data.source.remote.response.schedule_sholat.ScheduleResponse
import com.radea.alquranku.core.data.source.remote.response.surah.AyatItemResponse
import com.radea.alquranku.core.data.source.remote.response.surah.SurahItemResponse
import com.radea.alquranku.core.domain.model.Ayat
import com.radea.alquranku.core.domain.model.City
import com.radea.alquranku.core.domain.model.ScheduleSholat
import com.radea.alquranku.core.domain.model.Surah

object DataMapper {
    fun mapResponseToEntity(input: List<SurahItemResponse>): List<SurahEntity> {
        val surahLists = ArrayList<SurahEntity>()
        input.map {
            val surah = SurahEntity(
                surahNum = it.nomor,
                name = it.nama,
                latinName = it.namaLatin,
                place = it.tempatTurun,
                totalAyat = it.jumlahAyat,
                arti = it.arti,
                description = it.deskripsi,
                sourceAudio = it.audio
            )
            surahLists.add(surah)
        }
        return surahLists
    }

    fun mapEntitiesToDomain(input: List<SurahEntity>): List<Surah> =
        input.map {
            Surah(
                surahNum = it.surahNum,
                name = it.name,
                latinName = it.latinName,
                place = it.place,
                totalAyat = it.totalAyat,
                arti = it.arti,
                description = it.description,
                sourceAudio = it.sourceAudio
            )
        }

    fun mapAyatResponseToEntity(input: List<AyatItemResponse>): List<AyatEntity> {
        val ayatLists = ArrayList<AyatEntity>()
        input.map {
            val ayat = AyatEntity(
                id = it.id,
                surahNum = it.surah,
                ayatNum = it.nomor,
                arabic = it.ar,
                latin = it.tr,
                arti = it.idn
            )
            ayatLists.add(ayat)
        }
        return ayatLists
    }

    fun mapAyatEntityToDomain(input: List<AyatEntity>): List<Ayat> = input.map {
        Ayat(
            surahNum = it.surahNum,
            ayatNum = it.ayatNum,
            arabic = it.arabic,
            latin = it.latin,
            arti = it.arti
        )
    }

    fun mapCityResponseToEntity(input: List<CityDataItem>): List<CityEntity> {
        val cityLists = ArrayList<CityEntity>()
        input.map {
            val city = CityEntity(
                id = it.id.toInt(),
                name = it.lokasi
            )
            cityLists.add(city)
        }
        return cityLists
    }

    fun mapCityEntityToDomain(input: List<CityEntity>): List<City> = input.map {
        City(
            id = it.id,
            name = it.name
        )
    }

    fun mapScheduleResponseToEntity(inputDetailObj: DetailScheduleResponse): List<ScheduleEntity> {
        val scheduleLists = ArrayList<ScheduleEntity>()
        val arrayKey = ScheduleDataItem::class.java.declaredFields.map { it.name }
        val filteredArrayKey = arrayKey.filterNot { it.equals("tanggal") || it.equals("date") }
        filteredArrayKey.map { key ->
            val field = ScheduleDataItem::class.java.getDeclaredField(key)
            field.isAccessible = true
            val value = field.get(inputDetailObj.jadwal)
            field.isAccessible = false
            val schedule = ScheduleEntity(
                cityId = inputDetailObj.id,
                fullDate = inputDetailObj.jadwal.date,
                key = key,
                value = value?.toString() ?: "failed"
            )
            scheduleLists.add(schedule)
        }
        return scheduleLists
    }

    fun mapScheduleEntityToDomain(input: List<ScheduleEntity>): List<ScheduleSholat> = input.map {
        ScheduleSholat(
            key = it.key,
            value = it.value
        )
    }
}