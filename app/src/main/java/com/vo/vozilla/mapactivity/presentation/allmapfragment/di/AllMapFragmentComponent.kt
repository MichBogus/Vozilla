package com.vo.vozilla.mapactivity.presentation.allmapfragment.di

import com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation.AllMapFragment
import dagger.Subcomponent

@Subcomponent
interface AllMapFragmentComponent {

    fun inject(fragment: AllMapFragment)
}