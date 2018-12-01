package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single

interface VehicleMapInteractor {

    fun getVehicles(): Single<List<Pair<VehicleStatus, MarkerOptions>>>
}