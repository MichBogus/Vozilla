package com.vo.vozilla.repository.network.mapobjects

import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MapObjectsRetrofitApi {

    @Headers("Content-Type: application/json")
    @GET("map")
    fun getZones(@Query("objectType") objectType: String = "ZONE"): Single<ZonesMapObjectResponse>

    @Headers("Content-Type: application/json")
    @GET("map")
    fun getVehicles(@Query("objectType") objectType: String = "VEHICLE"): Single<VehiclesMapObjectResponse>

    @Headers("Content-Type: application/json")
    @GET("map")
    fun getParking(@Query("objectType") objectType: String = "PARKING"): Single<ParkingMapObjectResponse>
}