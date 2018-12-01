package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single
import javax.inject.Inject

class VehicleMapInteractorImpl
@Inject constructor(private val service: VehicleMapObjectService,
                    private val converter: VehicleToMarkerConverter) : VehicleMapInteractor {

    override fun getVehicles(): Single<List<Pair<VehicleStatus, MarkerOptions>>> =
            service.getVehicles().map { it.vehicles }.map { converter.convert(it) }
}