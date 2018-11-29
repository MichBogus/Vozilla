package com.vo.vozilla.repository.network.mapobjects.models.parking

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location

data class Parking(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("description") val description: String,
                   @SerializedName("location") val location: Location,
                   @SerializedName("address") val address: Address,
                   @SerializedName("spacesCount") val spacesCount: Int,
                   @SerializedName("availableSpacesCount") val availableSpacesCount: Int,
                   @SerializedName("color") val color: Color)