package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingSpace

interface ParkingMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadParking()
    }

    interface View {
        fun showParking(parkingList: List<Pair<ParkingSpace, MarkerOptions>>)
    }
}