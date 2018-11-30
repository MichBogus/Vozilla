package com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation

import com.google.android.gms.maps.model.PolygonOptions

interface ZoneMapFragmentMVP {

    interface Presenter {

        fun attach(view: ZoneMapFragmentMVP.View)
        fun detach()
        fun downloadZones()
    }

    interface View {

        fun showZones(polygons: List<PolygonOptions>)
    }
}