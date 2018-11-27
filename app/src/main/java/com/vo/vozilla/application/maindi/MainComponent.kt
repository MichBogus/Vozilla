package com.vo.vozilla.application.maindi

import com.vo.vozilla.application.VozillaApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface MainComponent {

    fun inject(cafApp: VozillaApp)
}