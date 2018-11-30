package com.vo.vozilla.ktextensions

import com.google.android.gms.maps.model.PolygonOptions
import org.assertj.core.api.Assertions.assertThat

fun assertTwoListsEquals(l1: List<PolygonOptions>, l2: List<PolygonOptions>) {
    l1.forEachIndexed { index, polygonOptions ->
        assertThat(polygonOptions.fillColor).isEqualTo(l2[index].fillColor)
        assertThat(polygonOptions.points).containsAll(l2[index].points)
    }
}