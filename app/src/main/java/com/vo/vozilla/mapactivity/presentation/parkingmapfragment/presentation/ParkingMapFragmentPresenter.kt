package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ParkingMapFragmentPresenter
@Inject constructor(private val interactor: ParkingMapInteractor,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : ParkingMapFragmentMVP.Presenter {

    private var view: ParkingMapFragmentMVP.View? = null
    private var compositeDisposable = CompositeDisposable()

    override fun attach(view: ParkingMapFragmentMVP.View) {
        this.view = view
    }

    override fun detach() {
        compositeDisposable.clear()
        view = null
    }

    override fun downloadParking() {
        interactor.getParking()
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                    view?.showParking(it)
                           }, {})
                .apply { compositeDisposable.add(this) }
    }
}