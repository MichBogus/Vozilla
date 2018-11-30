package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Single
import javax.inject.Inject

class VehicleMapInteractorImpl
@Inject constructor(private val service: VehicleMapObjectService,
                    private val converter: VehicleToMarkerConverter) : VehicleMapInteractor {

    override fun getVehicles(): Single<List<MarkerOptions>> =
            service.getVehicles().map { it.vehicles }.map { converter.convert(it) }
}