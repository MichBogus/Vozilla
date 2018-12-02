package com.vo.vozilla.mapactivity.presentation.converters

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus

interface VehicleToMarkerConverter {

    fun convert(vehicleList: List<Vehicle>): List<VehicleDomainModel>
    fun getMarkerDrawableByVehicleStatus(vehicleStatus: VehicleStatus): Int
}