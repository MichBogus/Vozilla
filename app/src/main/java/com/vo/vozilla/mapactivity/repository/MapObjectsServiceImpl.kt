package com.vo.vozilla.mapactivity.repository

import com.vo.vozilla.mapactivity.domain.MapObjectsService
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import io.reactivex.Single
import javax.inject.Inject

class MapObjectsServiceImpl
@Inject constructor(private val mapObjectsFeedApi: MapObjectsFeedApi) : MapObjectsService {

    override fun getZones(): Single<List<Zone>> =
            mapObjectsFeedApi.getZones().map { it.zones }
}