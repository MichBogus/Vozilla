package com.vo.vozilla.mapactivity.presentation.allmapfragment.di

import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractor
import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractorImpl
import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapObjectsService
import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragmentMVP
import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragmentPresenter
import com.vo.vozilla.mapactivity.presentation.allmapfragment.repository.AllMapObjectsServiceImpl
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverter
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AllMapFragmentModule {

    @Binds
    abstract fun bindPresenter(presenter: AllMapFragmentPresenter): AllMapFragmentMVP.Presenter

    @Binds
    abstract fun bindInteractor(interactor: AllMapFragmentInteractorImpl): AllMapFragmentInteractor

    @Binds
    abstract fun bindService(service: AllMapObjectsServiceImpl): AllMapObjectsService

    @Binds
    abstract fun bindParkingConverter(converter: ParkingToMarkerConverterImpl): ParkingToMarkerConverter

    @Binds
    abstract fun bindVehicleConverter(converter: VehicleToMarkerConverterImpl): VehicleToMarkerConverter

    @Binds
    abstract fun bindZoneConverter(converter: ZoneToPolygonConverterImpl): ZoneToPolygonConverter

}