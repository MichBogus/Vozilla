package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle

interface VehicleToMarkerConverter {

    fun convert(vehicleList: List<Vehicle>): List<MarkerOptions>
}