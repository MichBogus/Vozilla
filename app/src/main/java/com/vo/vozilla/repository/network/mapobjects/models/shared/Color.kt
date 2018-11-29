package com.vo.vozilla.repository.network.mapobjects.models

import com.google.gson.annotations.SerializedName

data class Color(@SerializedName("rgb") val rgb: String,
                 @SerializedName("alpha") val alpha: Float)