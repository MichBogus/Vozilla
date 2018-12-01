package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus

interface VehicleToMarkerConverter {

    fun convert(vehicleList: List<Vehicle>): List<Pair<VehicleStatus, MarkerOptions>>
    fun getMarkerDrawableByVehicleStatus(vehicleStatus: VehicleStatus): Int
}