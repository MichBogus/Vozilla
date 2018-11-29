package com.vo.vozilla.repository.network.mapobjects.models.vehicle

import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("street") val street: String,
                   @SerializedName("house") val houseNumber: String,
                   @SerializedName("city") val city: String)