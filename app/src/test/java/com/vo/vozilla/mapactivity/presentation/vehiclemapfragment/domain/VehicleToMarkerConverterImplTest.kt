package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.ktextensions.assertTwoMarkerOptionsListsEquals
import com.vo.vozilla.ktextensions.createData
import com.vo.vozilla.repository.network.mapobjects.models.Color
import com.vo.vozilla.repository.network.mapobjects.models.Location
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Address
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Picture
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class VehicleToMarkerConverterImplTest {

    private val tested = VehicleToMarkerConverterImpl()

    @Test
    fun shouldMapVehiclesResponseWithEmptyVehicles() {
        val expected = listOf<Pair<VehicleStatus, MarkerOptions>>()

        val actual = tested.convert(emptyListOfVehicles())

        assertTwoMarkerOptionsListsEquals(actual, expected)
    }

    @Test
    fun shouldMapZonesResponseToPolygons() {
        val expected = listOf(Pair(VehicleStatus.AVAILABLE, MarkerOptions().position(LatLng(1.0, 1.0))))

        val actual = tested.convert(vehicles())

        assertTwoMarkerOptionsListsEquals(actual, expected)
    }

    private fun emptyListOfVehicles() =
            listOf<Vehicle>()

    private fun vehicles() =
            listOf(Vehicle("1",
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
                           Color("2222222", 2f)))

    @RunWith(Parameterized::class)
    class ProperDrawableForVehicleStatusAvailable(val expected: Int,
                                                  val vehicleStatus: VehicleStatus) {

        private val tested = VehicleToMarkerConverterImpl()

        companion object {

            @JvmStatic
            @Parameterized.Parameters(name = """expected: {0} |
                                            vehicleStatus: {1}""")
            fun data() = createData({ arrayOf(R.drawable.ic_vehicle_mark, VehicleStatus.AVAILABLE) },
                                    { arrayOf(R.drawable.ic_vehicle_reserved_mark, VehicleStatus.RESERVED) },
                                    { arrayOf(R.drawable.ic_vehicle_rented_mark, VehicleStatus.RENTED) },
                                    { arrayOf(R.drawable.ic_vehicle_returned_mark, VehicleStatus.RETURNED) },
                                    { arrayOf(R.drawable.ic_vehicle_unavailable_mark, VehicleStatus.UNAVAILABLE) })
        }

        @Test
        fun shouldReturnProperDrawableForVehicleStatusAvailable() {
            val actual = tested.getMarkerDrawableByVehicleStatus(vehicleStatus)

            assertThat(actual).isEqualTo(expected)
        }

    }
}