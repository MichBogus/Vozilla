package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import io.reactivex.Single

interface VehicleMapInteractor {

    fun getVehicles(): Single<List<VehicleDomainModel>>
}