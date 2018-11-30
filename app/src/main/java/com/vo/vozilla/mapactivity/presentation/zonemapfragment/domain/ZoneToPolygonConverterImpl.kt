package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.di.ZoneMapConverterColorModule.AllowedColor
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.di.ZoneMapConverterColorModule.ExcludedColor
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import javax.inject.Inject

class ZoneToPolygonConverterImpl
@Inject constructor(@AllowedColor private val allowedZoneColor: Int,
                    @ExcludedColor private val excludedZoneColor: Int) : ZoneToPolygonConverter {

    override fun convert(it: List<Zone>): List<PolygonOptions> {
        val polygonList = mutableListOf<PolygonOptions>()
        it.map { zone ->
            polygonList.addAll(zone.allowedAreas.map {
                PolygonOptions()
                        .fillColor(allowedZoneColor)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())

            polygonList.addAll(zone.excludedAreas.map {
                PolygonOptions()
                        .fillColor(excludedZoneColor)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())
        }
        return polygonList
    }
}