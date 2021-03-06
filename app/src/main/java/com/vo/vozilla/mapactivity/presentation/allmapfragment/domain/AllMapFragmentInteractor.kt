package com.vo.vozilla.mapactivity.presentation.allmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import io.reactivex.Single

interface AllMapFragmentInteractor {

    fun getParking(): Single<List<Pair<ParkingSpace, MarkerOptions>>>

    fun getVehicles(): Single<List<VehicleDomainModel>>

    fun getZones(): Single<List<PolygonOptions>>
}