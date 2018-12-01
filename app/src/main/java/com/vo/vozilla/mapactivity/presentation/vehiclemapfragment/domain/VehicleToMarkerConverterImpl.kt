package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import javax.inject.Inject

class VehicleToMarkerConverterImpl
@Inject constructor() : VehicleToMarkerConverter {

    override fun convert(vehicleList: List<Vehicle>): List<Pair<VehicleStatus, MarkerOptions>> {
        val markersList = mutableListOf<Pair<VehicleStatus, MarkerOptions>>()
        vehicleList.forEach {
            markersList.add(Pair(it.status,
                                 MarkerOptions().position(LatLng(it.location.latitude, it.location.longitude))))
        }
        return markersList
    }

    override fun getMarkerDrawableByVehicleStatus(vehicleStatus: VehicleStatus) =
            when (vehicleStatus) {
                VehicleStatus.AVAILABLE -> R.drawable.ic_vehicle_mark
                VehicleStatus.RESERVED -> R.drawable.ic_vehicle_reserved_mark
                VehicleStatus.RENTED -> R.drawable.ic_vehicle_rented_mark
                VehicleStatus.RETURNED -> R.drawable.ic_vehicle_returned_mark
                VehicleStatus.UNAVAILABLE -> R.drawable.ic_vehicle_unavailable_mark
            }
}