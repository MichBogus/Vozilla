package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.google.android.gms.maps.model.MarkerOptions

interface VehicleMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadVehicles()
    }

    interface View {

        fun showVehicles(vehicleList: List<MarkerOptions>)
    }
}