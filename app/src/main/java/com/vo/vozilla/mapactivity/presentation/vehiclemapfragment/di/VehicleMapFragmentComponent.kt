package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.di

import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation.VehicleMapFragment
import dagger.Subcomponent

@Subcomponent(modules = [VehicleMapFragmentModule::class])
interface VehicleMapFragmentComponent {

    fun inject(fragment: VehicleMapFragment)
}