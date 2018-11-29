package com.vo.vozilla.mapactivity.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.mapactivity.domain.MapObjectsService
import com.vo.vozilla.mapactivity.domain.ZoneToPolygonConverter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapActivityPresenter
@Inject constructor(private val mapObjectsService: MapObjectsService,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : MapActivityMVP.Presenter {

    private var view: MapActivityMVP.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: MapActivityMVP.View) {
        this.view = view
    }

    override fun downloadZones() {
        mapObjectsService.getZones().observeOn(schedulerIO)
                .toObservable()
                .map {
                    ZoneToPolygonConverter().convert(it)
                }
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
