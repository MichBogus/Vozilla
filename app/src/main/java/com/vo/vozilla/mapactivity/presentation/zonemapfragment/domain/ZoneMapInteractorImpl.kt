package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverter
import io.reactivex.Single
import javax.inject.Inject

class ZoneMapInteractorImpl
@Inject constructor(private val service: ZoneMapObjectService,
                    private val converter: ZoneToPolygonConverter) : ZoneMapInteractor {

    override fun getZonesAsPolygons(): Single<List<PolygonOptions>> =
            service.getZones().map { it.zones }.map { converter.convert(it) }

}