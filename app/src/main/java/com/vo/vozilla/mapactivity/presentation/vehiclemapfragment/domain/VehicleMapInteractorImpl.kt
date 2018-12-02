package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import io.reactivex.Single
import javax.inject.Inject

class VehicleMapInteractorImpl
@Inject constructor(private val service: VehicleMapObjectService,
                    private val filtersService: FiltersService,
                    private val converter: VehicleToMarkerConverter) : VehicleMapInteractor {

    override fun getVehicles(): Single<List<VehicleDomainModel>> =
            service.getVehicles().map { it.vehicles }.map { converter.convert(it) }

    override fun getFilters(): Single<VehicleFilters> =
            filtersService.getFilters().map { VehicleFilters(it.filters.vehicleStatus, it.filters.vehicleModels) }

    override fun getVehiclesByModel(modelName: String): Single<List<VehicleDomainModel>> =
            service.getVehiclesByModel(modelName).map { it.vehicles }.map { converter.convert(it) }

    override fun getVehiclesByStatus(status: String): Single<List<VehicleDomainModel>> =
            service.getVehiclesByStatus(status).map { it.vehicles }.map { converter.convert(it) }

}