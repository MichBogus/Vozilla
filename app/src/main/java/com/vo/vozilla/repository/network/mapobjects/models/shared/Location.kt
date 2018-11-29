package com.vo.vozilla.repository.network.mapobjects.models

import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("latitude") val latitude: Double,
                    @SerializedName("longitude") val longitude: Double)