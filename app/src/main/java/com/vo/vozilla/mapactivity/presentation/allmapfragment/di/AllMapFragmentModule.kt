package com.vo.vozilla.mapactivity.presentation.allmapfragment.di

import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractor
import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractorImpl
import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragmentMVP
import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragmentPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class AllMapFragmentModule {

    @Binds
    abstract fun bindPresenter(presenter: AllMapFragmentPresenter): AllMapFragmentMVP.Presenter

    @Binds
    abstract fun bindInteractor(interactor: AllMapFragmentInteractorImpl): AllMapFragmentInteractor

}