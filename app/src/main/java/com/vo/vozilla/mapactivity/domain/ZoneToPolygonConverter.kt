package com.vo.vozilla.mapactivity.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.R
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone

class ZoneToPolygonConverter {

    fun convert(it: List<Zone>): List<PolygonOptions> {
        val polygonList = mutableListOf<PolygonOptions>()
        it.map {
            polygonList.addAll(it.allowedAreas.map {
                PolygonOptions()
                        .fillColor(R.color.colorPrimary)
                        .clickable(true)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())

            polygonList.addAll(it.excludedAreas.map {
                PolygonOptions()
                        .fillColor(R.color.colorAccent)
                        .clickable(true)
                        .addAll(it.points.map { LatLng(it.latitude, it.longitude) }.toList())
            }.toList())
        }
        return polygonList
    }
}