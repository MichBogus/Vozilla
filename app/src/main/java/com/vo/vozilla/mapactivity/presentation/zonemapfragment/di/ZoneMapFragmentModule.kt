package com.vo.vozilla.mapactivity.presentation.zonemapfragment.di

import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapInteractor
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapInteractorImpl
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapObjectService
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation.ZoneMapFragmentMVP
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation.ZoneMapFragmentPresenter
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.repository.ZoneMapObjectServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ZoneMapFragmentModule {

    @Binds
    abstract fun bindPresenter(presenter: ZoneMapFragmentPresenter): ZoneMapFragmentMVP.Presenter

    @Binds
    abstract fun bindInteractor(interactor: ZoneMapInteractorImpl): ZoneMapInteractor

    @Binds
    abstract fun bindZoneMapObjectService(service: ZoneMapObjectServiceImpl): ZoneMapObjectService
}