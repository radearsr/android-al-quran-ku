package com.radea.alquranku.core.data.source.remote.response.schedule_sholat

import com.google.gson.annotations.SerializedName

data class ListCityResponse(

	@field:SerializedName("request")
	val request: RequestResponse,

	@field:SerializedName("data")
	val data: List<CityDataItem>,

	@field:SerializedName("status")
	val status: Boolean
)
