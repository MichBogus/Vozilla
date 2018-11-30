package com.vo.vozilla.ktextensions

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView

    menuView.javaClass.getDeclaredField("mShiftingMode").apply {
        isAccessible = true
        setBoolean(menuView, false)
        isAccessible = false
    }

    @SuppressLint("RestrictedApi")
    for (i in 0 until menuView.childCount) {
        (menuView.getChildAt(i) as BottomNavigationItemView).apply {
            setShiftingMode(false)
            setChecked(false)
        }
    }
}