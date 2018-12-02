package com.vo.vozilla.mapactivity.presentation.converters

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking

interface ParkingToMarkerConverter {

    fun convert(parking: List<Parking>): List<Pair<ParkingSpace, MarkerOptions>>

    fun getPinBackgroundByParkingSpace(space: ParkingSpace): Int
}