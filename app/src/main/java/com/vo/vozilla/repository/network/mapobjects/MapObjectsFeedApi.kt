package com.vo.vozilla.repository.network.mapobjects

import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single

interface MapObjectsFeedApi {

    fun getZones(): Single<ZonesMapObjectResponse>

    fun getVehicles(): Single<VehiclesMapObjectResponse>

    fun getParking(): Single<ParkingMapObjectResponse>
}