package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Single

interface VehicleMapInteractor {

    fun getVehicles(): Single<List<MarkerOptions>>
}