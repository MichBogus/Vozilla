package com.vo.vozilla.repository.network.mapobjects.models.zone

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.Location

data class Area(@SerializedName("id") val id: String,
                @SerializedName("name") val name: String,
                @SerializedName("points") val points: List<Location>)