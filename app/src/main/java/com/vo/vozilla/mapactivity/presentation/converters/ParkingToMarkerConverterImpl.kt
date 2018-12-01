package com.vo.vozilla.mapactivity.presentation.converters

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingSpace
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking
import javax.inject.Inject

class ParkingToMarkerConverterImpl
@Inject constructor() : ParkingToMarkerConverter {

    override fun convert(parking: List<Parking>): List<Pair<ParkingSpace, MarkerOptions>> {
        val markersList = mutableListOf<Pair<ParkingSpace, MarkerOptions>>()
        parking.forEach {
            markersList.add(Pair(ParkingSpace(it.availableSpacesCount,
                                              it.spacesCount),
                                 MarkerOptions().position(LatLng(it.location.latitude, it.location.longitude))))
        }
        return markersList
    }

    override fun getPinBackgroundByParkingSpace(space: ParkingSpace) =
            if (space.availableSpace == 0) {
                R.drawable.ic_parking_no_more_space
            } else {
                R.drawable.ic_parking_space_left
            }
}