package com.vo.vozilla.repository.network.mapobjects.models

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking

data class ParkingMapObjectResponse(@SerializedName("parking") val parkings: List<Parking>)