package com.vo.vozilla.repository.network.filter.models

import com.google.gson.annotations.SerializedName

data class Filters(@SerializedName("VEHICLE_STATUS") val vehicleStatus: Map<String, String>,
              @SerializedName("VEHICLE_MODEL") val vehicleModels: Map<String, String>)