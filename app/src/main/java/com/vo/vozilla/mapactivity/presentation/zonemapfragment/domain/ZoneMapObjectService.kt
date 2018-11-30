package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single

interface ZoneMapObjectService {

    fun getZones(): Single<ZonesMapObjectResponse>
}