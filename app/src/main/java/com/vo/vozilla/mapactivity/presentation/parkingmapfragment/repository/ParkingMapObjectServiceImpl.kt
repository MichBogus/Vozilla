package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.repository

import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapObjectService
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import io.reactivex.Single
import javax.inject.Inject

class ParkingMapObjectServiceImpl
@Inject constructor(private val mapObjectsFeedApi: MapObjectsFeedApi) : ParkingMapObjectService {

    override fun getParking(): Single<ParkingMapObjectResponse> =
            mapObjectsFeedApi.getParking()
}