package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel

interface AllMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()

        fun downloadFullMap()
    }

    interface View {
        fun showParking(parking: List<Pair<ParkingSpace, MarkerOptions>>)
        fun showVehicles(vehicles: List<VehicleDomainModel>)
        fun showZones(zones: List<PolygonOptions>)

    }
}