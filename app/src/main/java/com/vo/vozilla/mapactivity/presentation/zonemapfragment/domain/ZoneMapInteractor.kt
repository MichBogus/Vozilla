package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.PolygonOptions
import io.reactivex.Single

interface ZoneMapInteractor {

    fun getZonesAsPolygons(): Single<List<PolygonOptions>>
}