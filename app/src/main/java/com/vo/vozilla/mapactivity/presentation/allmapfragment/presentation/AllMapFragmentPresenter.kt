package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AllMapFragmentPresenter
@Inject constructor(private val interactor: AllMapFragmentInteractor,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : AllMapFragmentMVP.Presenter {

    private var view: AllMapFragmentMVP.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: AllMapFragmentMVP.View) {
        this.view = view
    }

    override fun detach() {
        compositeDisposable.clear()
        view = null
    }

    override fun downloadFullMap() {
        interactor.getParking()
                .observeOn(schedulerUI)
                .map {
                    view?.showParking(it)
                    it
                }
                .observeOn(schedulerIO)
                .flatMap { interactor.getVehicles() }
                .observeOn(schedulerUI)
                .map {
                    view?.showVehicles(it)
                    it
                }
                .observeOn(schedulerIO)
                .flatMap { interactor.getZonesAsPolygons() }
                .observeOn(schedulerUI)
                .map {
                    view?.showZones(it)
                }
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({}, {})
                .apply { compositeDisposable.add(this) }
    }
}