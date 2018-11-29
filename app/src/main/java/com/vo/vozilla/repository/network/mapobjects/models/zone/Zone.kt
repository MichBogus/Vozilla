package com.vo.vozilla.repository.network.mapobjects.models.zone

import com.google.gson.annotations.SerializedName

data class Zone(@SerializedName("id") val id: String,
                @SerializedName("name") val name: String,
                @SerializedName("allowedAreas") val allowedAreas: List<Area>,
                @SerializedName("excludedAreas") val excludedAreas: List<Area>)