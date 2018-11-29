package com.vo.vozilla.mapactivity.di

import com.vo.vozilla.mapactivity.presentation.MapActivity
import dagger.Subcomponent

@Subcomponent(modules = [MapActivityModule::class])
interface MapActivityComponent {

    fun inject(activity: MapActivity)
}