package com.vo.vozilla.repository.network.mapobjects.models.vehicle

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location

data class Vehicle(@SerializedName(ID) val id: String,
                   @SerializedName(NAME) val name: String,
                   @SerializedName(PLATES_NUMBER) val platesNumber: String,
                   @SerializedName(SIDE_NUMBER) val sideNumber: String,
                   @SerializedName("color") val color: String,
                   @SerializedName("type") val type: String,
                   @SerializedName("picture") val picture: Picture,
                   @SerializedName("location") val location: Location,
                   @SerializedName("rangeKm") val rangeKm: Long,
                   @SerializedName("batteryLevelPct") val batteryLevelPct: Int,
                   @SerializedName("reservationEnd") val reservationEnd: String,
                   @SerializedName(STATUS) val status: VehicleStatus,
                   @SerializedName(LOCATION_DESCRIPTION) val locationDescription: String,
                   @SerializedName("address") val address: Address,
                   @SerializedName("mapColor") val mapColor: Color) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val PLATES_NUMBER = "platesNumber"
        const val SIDE_NUMBER = "sideNumber"
        const val STATUS = "status"
        const val LOCATION_DESCRIPTION = "locationDescription"
        const val PICTURE_ID = "pictureId"
    }
}