package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleFilters
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleMapInteractor
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class VehicleMapFragmentPresenterTest {

    private val dialogItemPickedObserver = PublishSubject.create<Pair<String, String>>()
    private val dialogItemPickedObservable: Observable<Pair<String, String>> = dialogItemPickedObserver
    private val interactorMock: VehicleMapInteractor = mock()
    private val viewMock: VehicleMapFragmentMVP.View = mock()

    private val tested = VehicleMapFragmentPresenter(interactorMock,
                                                     dialogItemPickedObservable,
                                                     Schedulers.trampoline(),
                                                     Schedulers.trampoline())

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
    fun shouldReturnProperMapOfVehicleModelsFilters() {
        val expected = mapOf(Pair("test3", "test"),
                             Pair("test4", "test"))

        tested.attach(viewMock)
        val actual = tested.getVehicleModelFilters()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldReturnProperMapOfVehicleStatusFilters() {
        val expected = mapOf(Pair("test1", "test"),
                             Pair("test2", "test"))

        tested.attach(viewMock)
        val actual = tested.getVehicleStatusFilters()

        assertThat(actual).isEqualTo(expected)
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

        verify(viewMock).clearMap()
        verify(viewMock).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldOnlyClearMapWhenListIsEmpty() {
        val expected = listOf<VehicleDomainModel>()
        whenever(interactorMock.getVehicles()).thenReturn(Single.just(expected))

        tested.attach(viewMock)
        tested.downloadVehicles()

        verify(viewMock).clearMap()
        verify(viewMock, times(0)).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldDownloadVehiclesBySelectedModel() {
        val expected = listOf(VehicleDomainModel(mutableMapOf<String, Any>().apply {
            put(Vehicle.ID, "1")
            put(Vehicle.NAME, "test-vehicle")
            put(Vehicle.PLATES_NUMBER, "222")
            put(Vehicle.SIDE_NUMBER, "223")
            put(Vehicle.STATUS, VehicleStatus.AVAILABLE.toString())
            put(Vehicle.LOCATION_DESCRIPTION, "test-location")
            put(Vehicle.PICTURE_ID, "2")
        }, VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getVehiclesByModel(any())).thenReturn(Single.just(expected))

        tested.attach(viewMock)

        dialogItemPickedObserver.onNext(Pair("test3", "test"))

        verify(interactorMock).getVehiclesByModel("test3")
        verify(viewMock).clearMap()
        verify(viewMock).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldDownloadVehiclesBySelectedModelAndOnlyClearMap() {
        val expected = listOf<VehicleDomainModel>()
        whenever(interactorMock.getVehiclesByModel(any())).thenReturn(Single.just(expected))

        tested.attach(viewMock)

        dialogItemPickedObserver.onNext(Pair("test3", "test"))

        verify(interactorMock).getVehiclesByModel("test3")
        verify(viewMock).clearMap()
        verify(viewMock, times(0)).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldDownloadVehiclesBySelectedStatus() {
        val expected = listOf(VehicleDomainModel(mutableMapOf<String, Any>().apply {
            put(Vehicle.ID, "1")
            put(Vehicle.NAME, "test-vehicle")
            put(Vehicle.PLATES_NUMBER, "222")
            put(Vehicle.SIDE_NUMBER, "223")
            put(Vehicle.STATUS, VehicleStatus.AVAILABLE.toString())
            put(Vehicle.LOCATION_DESCRIPTION, "test-location")
            put(Vehicle.PICTURE_ID, "2")
        }, VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getVehiclesByStatus(any())).thenReturn(Single.just(expected))

        tested.attach(viewMock)

        dialogItemPickedObserver.onNext(Pair("test1", "test"))

        verify(interactorMock).getVehiclesByStatus("test1")
        verify(viewMock).clearMap()
        verify(viewMock).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldDownloadVehiclesBySelectedStatusAndOnlyClearMap() {
        val expected = listOf<VehicleDomainModel>()
        whenever(interactorMock.getVehiclesByStatus(any())).thenReturn(Single.just(expected))

        tested.attach(viewMock)

        dialogItemPickedObserver.onNext(Pair("test1", "test"))

        verify(interactorMock).getVehiclesByStatus("test1")
        verify(viewMock).clearMap()
        verify(viewMock, times(0)).showVehicles(expected)
        verifyNoMoreInteractions(viewMock)
    }
}