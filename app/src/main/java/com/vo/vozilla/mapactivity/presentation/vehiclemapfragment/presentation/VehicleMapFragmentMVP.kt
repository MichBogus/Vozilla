package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus

interface VehicleMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadVehicles()
    }

    interface View {

        fun showVehicles(vehicleList: List<Pair<VehicleStatus, MarkerOptions>>)
    }
}