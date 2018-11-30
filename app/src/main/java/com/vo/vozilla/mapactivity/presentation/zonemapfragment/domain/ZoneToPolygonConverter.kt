package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import android.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone

class ZoneToPolygonConverter {

    private val allowedAreaColor = Color.argb(50, 111, 255, 111)
    private val excludedAreaColor = Color.argb(100, 255, 52, 52)

    fun convert(it: List<Zone>): List<PolygonOptions> {
        val polygonList = mutableListOf<PolygonOptions>()
        it.map { zone ->
            polygonList.addAll(zone.allowedAreas.map {
                PolygonOptions()
                        .fillColor(allowedAreaColor)
                        .clickable(true)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())

            polygonList.addAll(zone.excludedAreas.map {
                PolygonOptions()
                        .fillColor(excludedAreaColor)
                        .clickable(true)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())
        }
        return polygonList
    }
}