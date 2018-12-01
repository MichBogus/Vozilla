package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractor
import javax.inject.Inject

class AllMapFragmentPresenter
@Inject constructor(private val interactor: AllMapFragmentInteractor) : AllMapFragmentMVP.Presenter {

    private var view: AllMapFragmentMVP.View? = null

    override fun attach(view: AllMapFragmentMVP.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}