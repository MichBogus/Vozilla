package com.vo.vozilla.mapactivity.presentation.allmapfragment.repository

import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapObjectsService
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single
import javax.inject.Inject

class AllMapObjectsServiceImpl
@Inject constructor(private val mapObjectsFeedApi: MapObjectsFeedApi) : AllMapObjectsService {

    override fun getZones(): Single<ZonesMapObjectResponse> =
            mapObjectsFeedApi.getZones()

    override fun getVehicles(): Single<VehiclesMapObjectResponse> =
            mapObjectsFeedApi.getVehicles()

    override fun getParking(): Single<ParkingMapObjectResponse> =
            mapObjectsFeedApi.getParking()
}