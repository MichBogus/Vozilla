package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Single

interface ParkingMapInteractor {

    fun getParking(): Single<List<Pair<ParkingSpace, MarkerOptions>>>
}