package com.vo.vozilla.repository.network.mapobjects

import com.vo.vozilla.repository.network.base.BaseService
import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import javax.inject.Inject

class MapObjectsFeed
@Inject constructor(okHttpClient: OkHttpClient) : BaseService<MapObjectsRetrofitApi>(okHttpClient), MapObjectsFeedApi {

    override fun getGenericParameter(): Class<MapObjectsRetrofitApi> = MapObjectsRetrofitApi::class.java

    override fun getZones(): Single<ZonesMapObjectResponse> =
            restAdapter().getZones()

    override fun getVehicles(): Single<VehiclesMapObjectResponse> =
            restAdapter().getVehicles()

    override fun getParking(): Single<ParkingMapObjectResponse> =
            restAdapter().getParking()
}