package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.ktextensions.assertParkingTwoMarkerOptionsListsEquals
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.parking.Address
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Test

class ParkingMapInteractorImplTest {

    private val serviceMock: ParkingMapObjectService = mock()
    private val converterMock = ParkingToMarkerConverterImpl()

    private val tested = ParkingMapInteractorImpl(serviceMock, converterMock)

    @Test
    fun shouldMapParkingResponseWithEmptyParkingList() {
        val expected = listOf<Pair<ParkingSpace, MarkerOptions>>()
        whenever(serviceMock.getParking()).thenReturn(Single.just(responseWithEmptyListOfParking()))

        val testObserver = tested.getParking().test()

        Assertions.assertThat(testObserver.assertNoErrors().values().flatten()).isEmpty()
        assertParkingTwoMarkerOptionsListsEquals(testObserver.values().flatten(), expected)
    }

    @Test
    fun shouldMapParkingResponseToMarkers() {
        val expected = listOf(Pair(ParkingSpace(10, 20), MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(serviceMock.getParking()).thenReturn(Single.just(response()))

        val testObserver = tested.getParking().test()

        assertParkingTwoMarkerOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyListOfParking() =
            ParkingMapObjectResponse(listOf())

    private fun response() =
            ParkingMapObjectResponse(listOf(
                    Parking("1",
                            "test",
                            "test-desc",
                            Location(1.0, 1.0),
                            Address("test-street", "test-housenumber", "test-city"),
                            20,
                            10,
                            Color("222222", 2f))))


}