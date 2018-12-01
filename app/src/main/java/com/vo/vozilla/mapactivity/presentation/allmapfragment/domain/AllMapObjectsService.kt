package com.vo.vozilla.mapactivity.presentation.allmapfragment.domain

import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single

interface AllMapObjectsService {

    fun getZones(): Single<ZonesMapObjectResponse>

    fun getVehicles(): Single<VehiclesMapObjectResponse>

    fun getParking(): Single<ParkingMapObjectResponse>
}