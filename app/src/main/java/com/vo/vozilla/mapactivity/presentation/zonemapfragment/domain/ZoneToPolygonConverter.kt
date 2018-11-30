package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone

interface ZoneToPolygonConverter {

    fun convert(it: List<Zone>): List<PolygonOptions>
}