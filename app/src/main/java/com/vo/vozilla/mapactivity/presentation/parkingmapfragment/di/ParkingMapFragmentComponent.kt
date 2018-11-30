package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.di

import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation.ParkingMapFragment
import dagger.Subcomponent

@Subcomponent(modules = [ParkingMapFragmentModule::class])
interface ParkingMapFragmentComponent {

    fun inject(fragment: ParkingMapFragment)
}