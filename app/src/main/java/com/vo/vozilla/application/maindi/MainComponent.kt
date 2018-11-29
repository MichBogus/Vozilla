package com.vo.vozilla.application.maindi

import application.maindi.modules.NetworkModule
import com.vo.vozilla.application.VozillaApp
import com.vo.vozilla.mapactivity.di.MapActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface MainComponent {

    fun inject(cafApp: VozillaApp)

    fun plusMapActivityComponent(): MapActivityComponent
}