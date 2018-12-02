package com.vo.vozilla.mapactivity.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus

data class VehicleDomainModel(val dataModel: MutableMap<String, Any>,
                              val vehicleStatus: VehicleStatus,
                              val markerOption: MarkerOptions)