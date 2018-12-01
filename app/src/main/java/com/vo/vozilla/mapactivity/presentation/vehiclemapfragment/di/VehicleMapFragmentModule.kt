package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.di

import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractor
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractorImpl
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapObjectService
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation.VehicleMapFragmentMVP
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation.VehicleMapFragmentPresenter
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.repository.VehicleMapObjectServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class VehicleMapFragmentModule {

    @Binds
    abstract fun bindPresenter(presenter: VehicleMapFragmentPresenter): VehicleMapFragmentMVP.Presenter

    @Binds
    abstract fun bindInteractor(interactor: VehicleMapInteractorImpl): VehicleMapInteractor

    @Binds
    abstract fun bindService(service: VehicleMapObjectServiceImpl): VehicleMapObjectService

    @Binds
    abstract fun bindConverter(converter: VehicleToMarkerConverterImpl): VehicleToMarkerConverter
}