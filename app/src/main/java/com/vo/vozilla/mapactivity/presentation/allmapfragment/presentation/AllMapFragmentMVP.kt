package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

interface AllMapFragmentMVP {

    interface Presenter {

        fun attach(view: View)
        fun detach()
    }

    interface View {

    }
}