package com.vo.vozilla.mapactivity.presentation.zonemapfragment.repository

import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapObjectService
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import io.reactivex.Single
import javax.inject.Inject

class ZoneMapObjectServiceImpl
@Inject constructor(private val mapObjectsFeedApi: MapObjectsFeedApi) : ZoneMapObjectService {

    override fun getZones(): Single<ZonesMapObjectResponse> =
            mapObjectsFeedApi.getZones()
}
