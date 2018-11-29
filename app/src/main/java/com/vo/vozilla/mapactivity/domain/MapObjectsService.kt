package com.vo.vozilla.mapactivity.domain

import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import io.reactivex.Single

interface MapObjectsService {

    fun getZones(): Single<List<Zone>>
}