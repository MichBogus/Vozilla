package com.vo.vozilla.mapactivity.di

import com.vo.vozilla.mapactivity.presentation.MapActivity
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.di.ParkingMapFragmentComponent
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.di.VehicleMapFragmentComponent
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.di.ZoneMapFragmentComponent
import dagger.Subcomponent

@Subcomponent
interface MapActivityComponent {

    fun inject(activity: MapActivity)

    fun plusZoneMapFragmentComponent(): ZoneMapFragmentComponent
    fun plusParkingMapFragmentComponent(): ParkingMapFragmentComponent
    fun plusVehicleMapFragmentComponent(): VehicleMapFragmentComponent
}