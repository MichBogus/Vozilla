package com.vo.vozilla.mapactivity.presentation.allmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingSpace
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single

interface AllMapFragmentInteractor {

    fun getParking(): Single<List<Pair<ParkingSpace, MarkerOptions>>>

    fun getVehicles(): Single<List<Pair<VehicleStatus, MarkerOptions>>>

    fun getZonesAsPolygons(): Single<List<PolygonOptions>>
}