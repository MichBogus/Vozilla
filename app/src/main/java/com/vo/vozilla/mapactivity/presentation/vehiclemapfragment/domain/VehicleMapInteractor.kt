package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import io.reactivex.Single

interface VehicleMapInteractor {

    fun getVehicles(): Single<List<VehicleDomainModel>>

    fun getFilters(): Single<VehicleFilters>

    fun getVehiclesByModel(modelName: String): Single<List<VehicleDomainModel>>

    fun getVehiclesByStatus(status: String): Single<List<VehicleDomainModel>>
}