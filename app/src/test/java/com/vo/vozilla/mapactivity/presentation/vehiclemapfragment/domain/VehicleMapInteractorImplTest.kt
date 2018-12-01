package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.ktextensions.assertTwoMarkerOptionsListsEquals
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Address
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Picture
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Test

class VehicleMapInteractorImplTest {

    private val serviceMock: VehicleMapObjectService = mock()
    private val converterMock = VehicleToMarkerConverterImpl()

    private val tested = VehicleMapInteractorImpl(serviceMock, converterMock)

    @Test
    fun shouldMapVehiclesResponseWithEmptyVehicles() {
        val expected = listOf<Pair<VehicleStatus, MarkerOptions>>()
        whenever(serviceMock.getVehicles()).thenReturn(Single.just(responseWithEmptyListOfVehicles()))

        val testObserver = tested.getVehicles().test()

        Assertions.assertThat(testObserver.assertNoErrors().values().flatten()).isEmpty()
        assertTwoMarkerOptionsListsEquals(testObserver.values().flatten(), expected)
    }

    @Test
    fun shouldMapVehiclesResponseToMarkers() {
        val expected = listOf(Pair(VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(serviceMock.getVehicles()).thenReturn(Single.just(response()))

        val testObserver = tested.getVehicles().test()

        assertTwoMarkerOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyListOfVehicles() =
            VehiclesMapObjectResponse(listOf())

    private fun response() =
            VehiclesMapObjectResponse(listOf(
                    Vehicle("1",
                            "test-vehicle",
                            "222",
                            "223",
                            "blue",
                            "test-type",
                            Picture("2", "test-picture"),
                            Location(1.0, 1.0),
                            100,
                            20,
                            "test-end",
                            VehicleStatus.AVAILABLE, "test-location",
                            Address("test-street", "test-housenumber", "test-city"),
                            Color("2222222", 2f))))
}