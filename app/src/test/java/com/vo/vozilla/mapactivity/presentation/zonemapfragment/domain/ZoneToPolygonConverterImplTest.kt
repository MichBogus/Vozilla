package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.ktextensions.assertTwoPolygonOptionsListsEquals
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.zone.Area
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import org.junit.Test

class ZoneToPolygonConverterImplTest {

    private val tested = ZoneToPolygonConverterImpl(0, 1)

    @Test
    fun shouldMapZonesWithEmptyAreas() {
        val expected = listOf<PolygonOptions>()

        val actual = tested.convert(zoneWithEmptyAreas())

        assertTwoPolygonOptionsListsEquals(actual, expected)
    }

    @Test
    fun shouldMapZoneToPolygons() {
        val expected = listOf(PolygonOptions().fillColor(0).addAll(mutableListOf(LatLng(1.0, 1.0))),
                              PolygonOptions().fillColor(1).addAll(mutableListOf(LatLng(2.0, 2.0))))

        val actual = tested.convert(zone())

        assertTwoPolygonOptionsListsEquals(actual, expected)
    }

    private fun zoneWithEmptyAreas() =
            listOf(Zone("1", "test", listOf(), listOf()))

    private fun zone() =
            listOf(Zone("1", "test",
                        listOf(Area("1", "test-area-1",
                                    listOf(Location(1.0, 1.0)))),
                        listOf(Area("2", "test-area-2",
                                    listOf(Location(2.0, 2.0))))))
}