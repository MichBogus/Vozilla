package com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ZoneMapFragmentPresenter
@Inject constructor(private val interactor: ZoneMapInteractor,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : ZoneMapFragmentMVP.Presenter {

    private var view: ZoneMapFragmentMVP.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: ZoneMapFragmentMVP.View) {
        this.view = view
    }

    override fun downloadZones() {
        interactor.getZonesAsPolygons()
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                               view?.showZones(it)
                           }, { })
                .apply { compositeDisposable.add(this) }
    }

    override fun detach() {
        compositeDisposable.clear()
    }
}
