package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel

interface VehicleMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadVehicles()

        fun getVehicleModelFilters(): Map<String, String>
        fun getVehicleStatusFilters(): Map<String, String>
    }

    interface View {

        fun clearMap()
        fun showVehicles(vehicleList: List<VehicleDomainModel>)
    }
}