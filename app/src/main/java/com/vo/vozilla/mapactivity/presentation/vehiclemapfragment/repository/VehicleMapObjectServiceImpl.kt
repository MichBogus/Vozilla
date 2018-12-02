package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.repository

import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapObjectService
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import io.reactivex.Single
import javax.inject.Inject

class VehicleMapObjectServiceImpl
@Inject constructor(private val mapObjectsFeedApi: MapObjectsFeedApi) : VehicleMapObjectService {

    override fun getVehicles(): Single<VehiclesMapObjectResponse> =
            mapObjectsFeedApi.getVehicles()

    override fun getVehiclesByModel(modelName: String): Single<VehiclesMapObjectResponse> =
            mapObjectsFeedApi.getVehiclesByModel(modelName)

    override fun getVehiclesByStatus(status: String): Single<VehiclesMapObjectResponse> =
            mapObjectsFeedApi.getVehiclesByStatus(status)

}