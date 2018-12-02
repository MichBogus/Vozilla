package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleFilters
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleMapFragmentPresenter
@Inject constructor(private val interactor: VehicleMapInteractor,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : VehicleMapFragmentMVP.Presenter {

    private var view: VehicleMapFragmentMVP.View? = null
    private var filters: VehicleFilters? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: VehicleMapFragmentMVP.View) {
        this.view = view

        downloadFilters()
    }

    private fun downloadFilters() {
        interactor.getFilters()
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                               filters = it
                           }, { })
                .apply { compositeDisposable.add(this) }
    }

    override fun detach() {
        compositeDisposable.clear()
        filters = null
        view = null
    }

    override fun downloadVehicles() {
        interactor.getVehicles()
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                               view?.showVehicles(it)
                           }, { })
                .apply { compositeDisposable.add(this) }
    }
}