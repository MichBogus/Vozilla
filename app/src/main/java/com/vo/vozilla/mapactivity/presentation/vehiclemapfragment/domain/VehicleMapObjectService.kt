package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import io.reactivex.Single

interface VehicleMapObjectService {

    fun getVehicles(): Single<VehiclesMapObjectResponse>
}