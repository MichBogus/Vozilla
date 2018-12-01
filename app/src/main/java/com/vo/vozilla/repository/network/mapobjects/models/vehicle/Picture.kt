package com.vo.vozilla.repository.network.mapobjects.models.vehicle

import com.google.gson.annotations.SerializedName

data class Picture(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String)