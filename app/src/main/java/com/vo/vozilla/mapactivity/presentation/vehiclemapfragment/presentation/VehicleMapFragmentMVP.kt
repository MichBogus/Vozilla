package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.vo.vozilla.mapactivity.domain.VehicleDomainModel

interface VehicleMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadVehicles()
    }

    interface View {

        fun showVehicles(vehicleList: List<VehicleDomainModel>)
    }
}