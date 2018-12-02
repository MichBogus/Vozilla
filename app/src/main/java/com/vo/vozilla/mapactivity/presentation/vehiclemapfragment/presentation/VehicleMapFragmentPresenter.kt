package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerIO
import com.vo.vozilla.application.maindi.modules.SchedulerModule.SchedulerUI
import com.vo.vozilla.application.maindi.modules.SingleChoiceDialogModule.SingleChoiceItemPicked
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleFilters
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractor
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleMapFragmentPresenter
@Inject constructor(private val interactor: VehicleMapInteractor,
                    @SingleChoiceItemPicked private val dialogItemObservable: Observable<Pair<String, String>>,
                    @SchedulerIO private val schedulerIO: Scheduler,
                    @SchedulerUI private val schedulerUI: Scheduler) : VehicleMapFragmentMVP.Presenter {

    private var view: VehicleMapFragmentMVP.View? = null
    private var filters: VehicleFilters? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: VehicleMapFragmentMVP.View) {
        this.view = view

        downloadFilters()
        subscribeToDialogItemPickedObservable()
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

    private fun subscribeToDialogItemPickedObservable() {
        dialogItemObservable.observeOn(schedulerIO)
                .subscribeOn(schedulerUI)
                .subscribe { selected ->
                    filters?.let {
                        if (it.vehicleModels.containsKey(selected.first)) {
                            downloadVehiclesByModel(selected.first)
                        } else if (it.vehicleStatus.containsKey(selected.first)) {
                            downloadVehiclesByStatus(selected.first)
                        }
                    }
                }
                .apply { compositeDisposable.add(this) }
    }

    private fun downloadVehiclesByModel(model: String) {
        interactor.getVehiclesByModel(model)
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                               view?.clearMap()
                               if (it.isNotEmpty()) {
                                   view?.showVehicles(it)
                               }
                           }, {})
                .apply { compositeDisposable.add(this) }
    }

    private fun downloadVehiclesByStatus(status: String) {
        interactor.getVehiclesByStatus(status)
                .observeOn(schedulerUI)
                .subscribeOn(schedulerIO)
                .subscribe({
                               view?.clearMap()
                               if (it.isNotEmpty()) {
                                   view?.showVehicles(it)
                               }
                           }, {})
                .apply { compositeDisposable.add(this) }
    }

    override fun getVehicleModelFilters(): Map<String, String> = filters!!.vehicleModels

    override fun getVehicleStatusFilters(): Map<String, String> = filters!!.vehicleStatus

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
                               view?.clearMap()
                               if (it.isNotEmpty()) {
                                   view?.showVehicles(it)
                               }
                           }, { })
                .apply { compositeDisposable.add(this) }
    }
}