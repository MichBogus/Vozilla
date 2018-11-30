package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking

interface ParkingToMarkerConverter {

    fun convert(parking: List<Parking>): List<MarkerOptions>
}