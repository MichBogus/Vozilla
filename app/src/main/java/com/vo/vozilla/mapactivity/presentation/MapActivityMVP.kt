package com.vo.vozilla.mapactivity.presentation

import com.google.android.gms.maps.model.PolygonOptions

interface MapActivityMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
        fun downloadZones()
    }

    interface View {

        fun showZones(polygons: List<PolygonOptions>)
    }
}
