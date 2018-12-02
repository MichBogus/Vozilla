package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingMapInteractor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class ParkingMapFragmentPresenterTest {

    private val interactorMock: ParkingMapInteractor = mock()
    private val viewMock: ParkingMapFragmentMVP.View = mock()

    private val tested = ParkingMapFragmentPresenter(interactorMock, Schedulers.trampoline(), Schedulers.trampoline())

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
        val expected = listOf(Pair(ParkingSpace(1, 2), MarkerOptions().position(LatLng(1.0, 1.0))))
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))

        tested.attach(viewMock)
        tested.downloadParking()

        verify(viewMock).showParking(expected)
        verifyNoMoreInteractions(viewMock)
    }

    @Test
    fun shouldNotShowProperMarkerOptionsOnViewWhenListIsEmpty() {
        val expected = listOf<Pair<ParkingSpace, MarkerOptions>>()
        whenever(interactorMock.getParking()).thenReturn(Single.just(expected))

        tested.attach(viewMock)
        tested.downloadParking()

        verify(viewMock, times(0)).showParking(expected)
        verifyNoMoreInteractions(viewMock)
    }
}