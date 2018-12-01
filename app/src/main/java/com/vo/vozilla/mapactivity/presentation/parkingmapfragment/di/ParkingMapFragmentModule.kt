package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.di

import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapInteractor
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapInteractorImpl
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapObjectService
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation.ParkingMapFragmentMVP
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation.ParkingMapFragmentPresenter
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.repository.ParkingMapObjectServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ParkingMapFragmentModule {

    @Binds
    abstract fun bindPresenter(presenter: ParkingMapFragmentPresenter): ParkingMapFragmentMVP.Presenter

    @Binds
    abstract fun bindInteractor(interactor: ParkingMapInteractorImpl): ParkingMapInteractor

    @Binds
    abstract fun bindZoneMapObjectService(service: ParkingMapObjectServiceImpl): ParkingMapObjectService

    @Binds
    abstract fun bindConverter(converter: ParkingToMarkerConverterImpl): ParkingToMarkerConverter
}