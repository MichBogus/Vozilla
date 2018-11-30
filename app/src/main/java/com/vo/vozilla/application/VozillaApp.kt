package com.vo.vozilla.application

import android.support.multidex.MultiDexApplication
import com.vo.vozilla.application.maindi.DaggerMainComponent
import com.vo.vozilla.application.maindi.MainComponent

class VozillaApp : MultiDexApplication() {

    var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
                .bindContext(this)
                .build()
    }
}