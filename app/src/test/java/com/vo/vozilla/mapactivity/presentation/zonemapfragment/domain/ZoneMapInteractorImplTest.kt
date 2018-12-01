package com.vo.vozilla.mapactivity.presentation.zonemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vo.vozilla.ktextensions.assertTwoPolygonOptionsListsEquals
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverterImpl
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.ZonesMapObjectResponse
import com.vo.vozilla.repository.network.mapobjects.models.zone.Area
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ZoneMapInteractorImplTest {

    private val serviceMock: ZoneMapObjectService = mock()
    private val converterMock: ZoneToPolygonConverterImpl = ZoneToPolygonConverterImpl(
            0,
            1)

    private val tested = ZoneMapInteractorImpl(serviceMock, converterMock)

    @Test
    fun shouldMapZonesResponseWithEmptyAreas() {
        val expected = listOf<PolygonOptions>()
        whenever(serviceMock.getZones()).thenReturn(Single.just(responseWithEmptyAreas()))

        val testObserver = tested.getZonesAsPolygons().test()

        assertThat(testObserver.assertNoErrors().values().flatten()).isEmpty()
        assertTwoPolygonOptionsListsEquals(testObserver.values().flatten(), expected)
    }

    @Test
    fun shouldMapZonesResponseToPolygons() {
        val expected = listOf(PolygonOptions().fillColor(0).addAll(mutableListOf(LatLng(1.0, 1.0))),
                              PolygonOptions().fillColor(1).addAll(mutableListOf(LatLng(2.0, 2.0))))
        whenever(serviceMock.getZones()).thenReturn(Single.just(response()))

        val testObserver = tested.getZonesAsPolygons().test()

        assertTwoPolygonOptionsListsEquals(testObserver.assertNoErrors().values().flatten(), expected)
    }

    private fun responseWithEmptyAreas() =
            ZonesMapObjectResponse(listOf(
                    Zone("1", "test", listOf(), listOf())))

    private fun response() =
            ZonesMapObjectResponse(listOf(
                    Zone("1", "test",
                         listOf(Area("1", "test-area-1",
                                     listOf(Location(1.0, 1.0)))),
                         listOf(Area("2", "test-area-2",
                                     listOf(Location(2.0, 2.0)))))))
}