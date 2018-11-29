package com.vo.vozilla.repository.network.mapobjects.models

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle

data class VehiclesMapObjectResponse(@SerializedName("vehicles") val vehicles: List<Vehicle>)