package com.vo.vozilla.repository.network.mapobjects.models.vehicle

import com.google.gson.annotations.SerializedName

enum class VehicleStatus {

    @SerializedName("AVAILABLE")
    AVAILABLE,
    @SerializedName("RESERVED")
    RESERVED,
    @SerializedName("RENTED")
    RENTED,
    @SerializedName("RETURNED")
    RETURNED,
    @SerializedName("UNAVAILABLE")
    UNAVAILABLE,
    @SerializedName("MAINTENANCE")
    MAINTENANCE;
}