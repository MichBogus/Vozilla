package com.vo.vozilla.mapactivity.presentation.allmapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.ktextensions.assertParkingTwoMarkerOptionsListsEquals
import com.vo.vozilla.ktextensions.assertTwoMarkerOptionsListsEquals
import com.vo.vozilla.ktextensions.assertTwoPolygonOptionsListsEquals
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverterImpl
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.ParkingMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.VehiclesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.parking.Address
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Picture
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import com.vo.vozilla.repository.network.mapobjects.models.zone.Area
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Test

class AllMapFragmentInteractorImplTest {

    private val serviceMock: AllMapObjectsService = mock()
    private val parkingConverterMock = ParkingToMarkerConverterImpl()
    private val zoneConverterMock = ZoneToPolygonConverterImpl(0, 1)
    private val vehicleConverterMock = VehicleToMarkerConverterImpl()

    private val tested = AllMapFragmentInteractorImpl(serviceMock,
                                                      zoneConverterMock,
                                                      vehicleConverterMock,
                                                      parkingConverterMock)

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
        whenever(serviceMock.getParking()).thenReturn(Single.just(parkingResponse()))

        val testObserver = tested.getParking().test()

        assertParkingTwoMarkerOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyListOfParking() =
            ParkingMapObjectResponse(listOf())

    private fun parkingResponse() =
            ParkingMapObjectResponse(listOf(
                    Parking("1",
                            "test",
                            "test-desc",
                            Location(1.0, 1.0),
                            Address("test-street", "test-housenumber", "test-city"),
                            20,
                            10,
                            Color("222222", 2f))))

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
        whenever(serviceMock.getVehicles()).thenReturn(Single.just(vehicleResponse()))

        val testObserver = tested.getVehicles().test()

        assertTwoMarkerOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyListOfVehicles() =
            VehiclesMapObjectResponse(listOf())

    private fun vehicleResponse() =
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
                            com.vo.vozilla.repository.network.mapobjects.models.vehicle.Address("test-street",
                                                                                                "test-housenumber",
                                                                                                "test-city"),
                            Color("2222222", 2f))))

    @Test
    fun shouldMapZonesResponseWithEmptyAreas() {
        val expected = listOf<PolygonOptions>()
        whenever(serviceMock.getZones()).thenReturn(Single.just(responseWithEmptyAreas()))

        val testObserver = tested.getZones().test()

        Assertions.assertThat(testObserver.assertNoErrors().values().flatten()).isEmpty()
        assertTwoPolygonOptionsListsEquals(testObserver.values().flatten(), expected)
    }

    @Test
    fun shouldMapZonesResponseToPolygons() {
        val expected = listOf(PolygonOptions().fillColor(0).addAll(mutableListOf(LatLng(1.0, 1.0))),
                              PolygonOptions().fillColor(1).addAll(mutableListOf(LatLng(2.0, 2.0))))
        whenever(serviceMock.getZones()).thenReturn(Single.just(zoneResponse()))

        val testObserver = tested.getZones().test()

        assertTwoPolygonOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyAreas() =
            ZonesMapObjectResponse(listOf(
                    Zone("1", "test", listOf(), listOf())))

    private fun zoneResponse() =
            ZonesMapObjectResponse(listOf(
                    Zone("1", "test",
                         listOf(Area("1", "test-area-1",
                                     listOf(Location(1.0, 1.0)))),
                         listOf(Area("2", "test-area-2",
                                     listOf(Location(2.0, 2.0)))))))
}