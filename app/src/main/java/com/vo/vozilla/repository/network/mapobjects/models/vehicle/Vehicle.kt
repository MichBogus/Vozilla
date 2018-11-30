package com.vo.vozilla.repository.network.mapobjects.models.vehicle

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location

data class Vehicle(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("platesNumber") val platesNumber: String,
                   @SerializedName("sideNumber") val sideNumber: String,
                   @SerializedName("color") val color: String,
                   @SerializedName("type") val type: String,
                   @SerializedName("picture") val picture: Picture,
                   @SerializedName("location") val location: Location,
                   @SerializedName("rangeKm") val rangeKm: Long,
                   @SerializedName("batteryLevelPct") val batteryLevelPct: Int,
                   @SerializedName("reservationEnd") val reservationEnd: String,
                   @SerializedName("status") val status: VehicleStatus,
                   @SerializedName("locationDescription") val locationDescription: String,
                   @SerializedName("address") val address: Address,
                   @SerializedName("mapColor") val mapColor: Color)