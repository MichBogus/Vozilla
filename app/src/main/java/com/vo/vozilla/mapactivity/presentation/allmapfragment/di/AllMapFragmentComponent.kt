package com.vo.vozilla.mapactivity.presentation.allmapfragment.di

import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragment
import dagger.Subcomponent

@Subcomponent(modules = [AllMapFragmentModule::class])
interface AllMapFragmentComponent {

    fun inject(fragment: AllMapFragment)
}