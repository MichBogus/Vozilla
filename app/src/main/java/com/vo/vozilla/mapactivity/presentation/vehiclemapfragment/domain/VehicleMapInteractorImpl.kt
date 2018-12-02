package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import io.reactivex.Single
import javax.inject.Inject

class VehicleMapInteractorImpl
@Inject constructor(private val service: VehicleMapObjectService,
                    private val converter: VehicleToMarkerConverter) : VehicleMapInteractor {

    override fun getVehicles(): Single<List<VehicleDomainModel>> =
            service.getVehicles().map { it.vehicles }.map { converter.convert(it) }
}