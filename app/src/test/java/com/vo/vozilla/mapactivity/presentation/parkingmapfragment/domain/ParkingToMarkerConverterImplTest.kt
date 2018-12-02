package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.ktextensions.assertParkingTwoMarkerOptionsListsEquals
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.parking.Address
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ParkingToMarkerConverterImplTest {

    private val tested = ParkingToMarkerConverterImpl()

    @Test
    fun shouldMapParkingResponseWithEmptyParkingList() {
        val expected = listOf<Pair<ParkingSpace, MarkerOptions>>()

        val actual = tested.convert(emptyListOfParking())

        assertParkingTwoMarkerOptionsListsEquals(actual, expected)
    }

    @Test
    fun shouldMapZonesResponseToPolygons() {
        val expected = listOf(Pair(ParkingSpace(10, 20), MarkerOptions().position(LatLng(1.0, 1.0))))

        val actual = tested.convert(parking())

        assertParkingTwoMarkerOptionsListsEquals(actual, expected)
    }

    @Test
    fun shouldReturnOutOfSpaceDrawableId() {
        val expected = R.drawable.ic_parking_no_more_space
        val actual = tested.getPinBackgroundByParkingSpace(ParkingSpace(0, 10))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldReturnSpaceAvailableDrawableId() {
        val expected = R.drawable.ic_parking_space_left
        val actual = tested.getPinBackgroundByParkingSpace(ParkingSpace(1, 10))

        assertThat(actual).isEqualTo(expected)
    }

    private fun emptyListOfParking() =
            listOf<Parking>()

    private fun parking() =
            listOf(Parking("1",
                           "test",
                           "test-desc",
                           Location(1.0, 1.0),
                           Address("test-street", "test-housenumber", "test-city"),
                           20,
                           10,
                           Color("222222", 2f)))
}