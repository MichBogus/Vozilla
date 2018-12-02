package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.ktextensions.assertVehicleFiltersEquals
import com.vo.vozilla.ktextensions.assertVehicleListsEquals
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverterImpl
import com.vo.vozilla.repository.network.filter.models.Filters
import com.vo.vozilla.repository.network.filter.models.FiltersResponse
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
    private val filtersServiceMock: FiltersService = mock()
    private val converterMock = VehicleToMarkerConverterImpl()

    private val tested = VehicleMapInteractorImpl(serviceMock, filtersServiceMock, converterMock)

    @Test
    fun shouldMapVehiclesResponseWithEmptyVehicles() {
        val expected = listOf<VehicleDomainModel>()
        whenever(serviceMock.getVehicles()).thenReturn(Single.just(responseWithEmptyListOfVehicles()))

        val testObserver = tested.getVehicles().test()

        Assertions.assertThat(testObserver.assertNoErrors().values().flatten()).isEmpty()
        assertVehicleListsEquals(testObserver.values().flatten(), expected)
    }

    @Test
    fun shouldMapVehiclesResponseToMarkers() {
        val expected = listOf(VehicleDomainModel(mutableMapOf<String, Any>().apply {
            put(Vehicle.ID, "1")
            put(Vehicle.NAME, "test-vehicle")
            put(Vehicle.PLATES_NUMBER, "222")
            put(Vehicle.SIDE_NUMBER, "223")
            put(Vehicle.STATUS, VehicleStatus.AVAILABLE.toString())
            put(Vehicle.LOCATION_DESCRIPTION, "test-location")
            put(Vehicle.PICTURE_ID, "2")
        }, VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(serviceMock.getVehicles()).thenReturn(Single.just(response()))

        val testObserver = tested.getVehicles().test()

        assertVehicleListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
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

    @Test
    fun shouldReturnProperFilters() {
        val expected = VehicleFilters(mapOf(Pair("test1", "test"), Pair("test2", "test")),
                                      mapOf(Pair("test3", "test"), Pair("test4", "test")))
        whenever(filtersServiceMock.getFilters()).thenReturn(Single.just(filtersResponse()))

        val testObserver = tested.getFilters().test()

        assertVehicleFiltersEquals(testObserver.assertNoErrors().values().first(), expected)
    }

    private fun filtersResponse() =
            FiltersResponse(Filters(mapOf(Pair("test1", "test"), Pair("test2", "test")),
                                    mapOf(Pair("test3", "test"), Pair("test4", "test"))))
}