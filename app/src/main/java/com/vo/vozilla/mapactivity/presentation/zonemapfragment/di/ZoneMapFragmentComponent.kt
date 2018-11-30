package com.vo.vozilla.mapactivity.presentation.zonemapfragment.di

import com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation.ZoneMapFragment
import dagger.Subcomponent

@Subcomponent(modules = [ZoneMapFragmentModule::class,
    ZoneMapConverterColorModule::class])
interface ZoneMapFragmentComponent {

    fun inject(fragment: ZoneMapFragment)
}