package com.vo.vozilla.mapactivity.di

import com.vo.vozilla.mapactivity.domain.MapObjectsService
import com.vo.vozilla.mapactivity.presentation.MapActivityMVP
import com.vo.vozilla.mapactivity.presentation.MapActivityPresenter
import com.vo.vozilla.mapactivity.repository.MapObjectsServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MapActivityModule {

    @Binds
    abstract fun bindPresenter(presenter: MapActivityPresenter): MapActivityMVP.Presenter

    @Binds
    abstract fun bindMapObjectsService(service: MapObjectsServiceImpl): MapObjectsService
}