package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import io.reactivex.Single

interface ParkingMapObjectService {

    fun getParking(): Single<ParkingMapObjectResponse>
}