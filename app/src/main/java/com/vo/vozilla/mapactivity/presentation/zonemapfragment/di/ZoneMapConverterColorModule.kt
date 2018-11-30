package com.vo.vozilla.mapactivity.presentation.zonemapfragment.di

import android.graphics.Color
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class ZoneMapConverterColorModule {

    @Provides
    @AllowedColor
    fun providesAllowedColor(): Int = Color.argb(50, 111, 255, 111)

    @Provides
    @ExcludedColor
    fun providesExcludedColor(): Int = Color.argb(100, 255, 52, 52)

    @Qualifier
    annotation class AllowedColor

    @Qualifier
    annotation class ExcludedColor
}