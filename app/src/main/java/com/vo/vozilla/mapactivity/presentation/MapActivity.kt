package com.vo.vozilla.mapactivity.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vo.vozilla.R
import com.vo.vozilla.baseactivity.BaseActivity
import com.vo.vozilla.ktextensions.disableShiftMode
import kotlinx.android.synthetic.main.activity_main.*

class MapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setupWithNavController(findNavController(R.id.container))
        bottom_navigation.disableShiftMode()
    }

    override fun onSupportNavigateUp() =
            findNavController(R.id.container).navigateUp()

    fun getMapActivityComponent() = vozillaMainComponent().plusMapActivityComponent()
}
