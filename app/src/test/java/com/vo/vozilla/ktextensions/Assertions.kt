package com.vo.vozilla.ktextensions

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleFilters
import org.assertj.core.api.Assertions.assertThat

fun assertTwoPolygonOptionsListsEquals(l1: List<PolygonOptions>, l2: List<PolygonOptions>) {
    assertThat(l1).hasSize(l2.size)
    l1.forEachIndexed { index, polygonOptions ->
        assertThat(polygonOptions.fillColor).isEqualTo(l2[index].fillColor)
        assertThat(polygonOptions.points).containsAll(l2[index].points)
    }
}

fun assertVehicleListsEquals(l1: List<VehicleDomainModel>,
                             l2: List<VehicleDomainModel>) {
    assertThat(l1).hasSize(l2.size)
    l1.forEachIndexed { index, markerOption ->
        assertThat(markerOption.dataModel).isEqualTo(l2[index].dataModel)
        assertThat(markerOption.vehicleStatus).isEqualTo(l2[index].vehicleStatus)
        assertThat(markerOption.markerOption.position).isEqualTo(l2[index].markerOption.position)
    }
}

fun assertParkingListsEquals(l1: List<Pair<ParkingSpace, MarkerOptions>>,
                             l2: List<Pair<ParkingSpace, MarkerOptions>>) {
    assertThat(l1).hasSize(l2.size)
    l1.forEachIndexed { index, markerOption ->
        assertThat(markerOption.first).isEqualTo(l2[index].first)
        assertThat(markerOption.second.position).isEqualTo(l2[index].second.position)
    }
}

fun assertVehicleFiltersEquals(f1: VehicleFilters, f2: VehicleFilters) {
    assertThat(f1.vehicleModels).isEqualTo(f2.vehicleModels)
    assertThat(f1.vehicleStatus).isEqualTo(f2.vehicleStatus)
}