package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.allmapfragment.domain.AllMapFragmentInteractor
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class AllMapFragmentPresenterTest {

    private val interactorMock: AllMapFragmentInteractor = mock()
    private val viewMock: AllMapFragmentMVP.View = mock()

    private val tested = AllMapFragmentPresenter(interactorMock, Schedulers.trampoline(), Schedulers.trampoline())

    @Test
    fun shouldAttachViewCorrectly() {
        tested.attach(viewMock)

        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldDetachViewCorrectly() {
        tested.attach(viewMock)
        tested.detach()

        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldShowOnlyParkingWhenVehiclesEndpointHasError() {
        val expected = listOf(Pair(ParkingSpace(1, 2), MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))
        whenever(interactorMock.getVehicles()).thenReturn(Single.error(IllegalStateException()))

        tested.attach(viewMock)
        tested.downloadFullMap()

        verify(viewMock).showParking(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldShowParkingAndVehiclesWhenZonesEndpointHasError() {
        val expected = listOf(Pair(ParkingSpace(1, 2), MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))
        val vehicleExpected = listOf(VehicleDomainModel(mutableMapOf(),
                                                        VehicleStatus.AVAILABLE,
                                                        MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getVehicles()).thenReturn(Single.just(vehicleExpected))
        whenever(interactorMock.getZones()).thenReturn(Single.error(IllegalStateException()))

        tested.attach(viewMock)
        tested.downloadFullMap()

        verify(viewMock).showParking(expected)
        verify(viewMock).showVehicles(vehicleExpected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldShowFullMap() {
        val expected = listOf(Pair(ParkingSpace(1, 2), MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))
        val vehicleExpected = listOf(VehicleDomainModel(mutableMapOf(),
                                                        VehicleStatus.AVAILABLE,
                                                        MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getVehicles()).thenReturn(Single.just(vehicleExpected))
        val zonesExpected = listOf(PolygonOptions().add(LatLng(1.0, 1.0)))
        whenever(interactorMock.getZones()).thenReturn(Single.just(zonesExpected))

        tested.attach(viewMock)
        tested.downloadFullMap()

        verify(viewMock).showParking(expected)
        verify(viewMock).showVehicles(vehicleExpected)
        verify(viewMock).showZones(zonesExpected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldNotShowAnyItemsWhenResponseHasEmptyLists() {
        val expected = listOf<Pair<ParkingSpace, MarkerOptions>>()
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))
        val vehicleExpected = listOf<VehicleDomainModel>()
        whenever(interactorMock.getVehicles()).thenReturn(Single.just(vehicleExpected))
        val zonesExpected = listOf<PolygonOptions>()
        whenever(interactorMock.getZones()).thenReturn(Single.just(zonesExpected))

        tested.attach(viewMock)
        tested.downloadFullMap()

        verify(viewMock, times(0)).showParking(expected)
        verify(viewMock, times(0)).showVehicles(vehicleExpected)
        verify(viewMock, times(0)).showZones(zonesExpected)
        verifyNoMoreInteractions(viewMock)
    }
}