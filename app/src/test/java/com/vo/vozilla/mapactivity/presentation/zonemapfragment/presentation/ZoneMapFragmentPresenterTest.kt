package com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain.ZoneMapInteractor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class ZoneMapFragmentPresenterTest {

    private val interactorMock: ZoneMapInteractor = mock()
    private val viewMock: ZoneMapFragmentMVP.View = mock()

    private val tested = ZoneMapFragmentPresenter(interactorMock, Schedulers.trampoline(), Schedulers.trampoline())

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
    fun shouldShowProperPolygonOptionsOnView() {
        val expected = listOf(PolygonOptions().add(LatLng(1.0, 1.0)))
        whenever(interactorMock.getZonesAsPolygons()).thenReturn(Single.just(expected))

        tested.attach(viewMock)
        tested.downloadZones()

        verify(viewMock).showZones(expected)
        verifyNoMoreInteractions(viewMock)
    }

}