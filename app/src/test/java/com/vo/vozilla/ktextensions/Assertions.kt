package com.vo.vozilla.ktextensions

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import org.assertj.core.api.Assertions.assertThat

fun assertTwoPolygonOptionsListsEquals(l1: List<PolygonOptions>, l2: List<PolygonOptions>) {
    assertThat(l1).hasSize(l2.size)
    l1.forEachIndexed { index, polygonOptions ->
        assertThat(polygonOptions.fillColor).isEqualTo(l2[index].fillColor)
        assertThat(polygonOptions.points).containsAll(l2[index].points)
    }
}

fun assertTwoMarkerOptionsListsEquals(l1: List<Pair<VehicleStatus, MarkerOptions>>,
                                      l2: List<Pair<VehicleStatus, MarkerOptions>>) {
    assertThat(l1).hasSize(l2.size)
    l1.forEachIndexed { index, markerOption ->
        assertThat(markerOption.first).isEqualTo(l2[index].first)
        assertThat(markerOption.second.position).isEqualTo(l2[index].second.position)
    }
}