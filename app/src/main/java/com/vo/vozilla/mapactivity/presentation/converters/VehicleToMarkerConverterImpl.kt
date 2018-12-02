package com.vo.vozilla.mapactivity.presentation.converters

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import javax.inject.Inject

class VehicleToMarkerConverterImpl
@Inject constructor() : VehicleToMarkerConverter {

    override fun convert(vehicleList: List<Vehicle>): List<VehicleDomainModel> {
        val markersList = mutableListOf<VehicleDomainModel>()
        vehicleList.forEach {
            val dataModel = mutableMapOf<String, Any>().apply {
                put(Vehicle.ID, it.id)
                put(Vehicle.NAME, it.name)
                put(Vehicle.PLATES_NUMBER, it.platesNumber)
                put(Vehicle.SIDE_NUMBER, it.sideNumber)
                put(Vehicle.STATUS, it.status.toString())
                put(Vehicle.LOCATION_DESCRIPTION, it.locationDescription)
                put(Vehicle.PICTURE_ID, it.picture.id)
            }
            markersList.add(VehicleDomainModel(dataModel,
                                                                                 it.status,
                                                                                 MarkerOptions().position(LatLng(it.location.latitude,
                                                                                                                 it.location.longitude))))
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