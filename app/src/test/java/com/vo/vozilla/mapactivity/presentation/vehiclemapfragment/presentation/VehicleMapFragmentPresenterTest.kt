package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleFilters
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractor
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class VehicleMapFragmentPresenterTest {

    private val interactorMock: VehicleMapInteractor = mock()
    private val viewMock: VehicleMapFragmentMVP.View = mock()

    private val tested = VehicleMapFragmentPresenter(interactorMock, Schedulers.trampoline(), Schedulers.trampoline())

    @Before
    fun setUp() {
        whenever(interactorMock.getFilters()).thenReturn(Single.just(VehicleFilters(mapOf(Pair("test1", "test"),
                                                                                          Pair("test2", "test")),
                                                                                    mapOf(Pair("test3", "test"),
                                                                                          Pair("test4", "test"))))
        )
    }

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
    fun shouldShowProperMarkerOptionsOnView() {
        val expected = listOf(VehicleDomainModel(mutableMapOf<String, Any>().apply {
            put(Vehicle.ID, "1")
            put(Vehicle.NAME, "test-vehicle")
            put(Vehicle.PLATES_NUMBER, "222")
            put(Vehicle.SIDE_NUMBER, "223")
            put(Vehicle.STATUS, VehicleStatus.AVAILABLE.toString())
            put(Vehicle.LOCATION_DESCRIPTION, "test-location")
            put(Vehicle.PICTURE_ID, "2")
        }, VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getVehicles()).thenReturn(Single.just(expected))

        tested.attach(viewMock)
        tested.downloadVehicles()

        verify(viewMock).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }
}