package com.vo.vozilla.baseactivity

import android.support.v7.app.AppCompatActivity
import com.vo.vozilla.application.VozillaApp

abstract class BaseActivity: AppCompatActivity() {

    fun vozillaMainComponent() = (application as VozillaApp).mainComponent!!
}