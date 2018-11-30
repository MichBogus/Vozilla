package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import android.content.Context
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import com.vo.vozilla.R
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import javax.inject.Inject

class VehicleToMarkerConverterImpl
@Inject constructor(private val context: Context) : VehicleToMarkerConverter {

    override fun convert(vehicleList: List<Vehicle>): List<MarkerOptions> {
        val markersList = mutableListOf<MarkerOptions>()
        vehicleList.forEach {
            val iconGenerator = IconGenerator(context).apply {
                setBackground(getMarkerDrawableByVehicleStatus(it.status))
            }
            val iconBitmap = iconGenerator.makeIcon()

            markersList.add(MarkerOptions().position(LatLng(it.location.latitude, it.location.longitude))
                                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap)))
        }
        return markersList
    }

    private fun getMarkerDrawableByVehicleStatus(vehicleStatus: VehicleStatus) =
            when (vehicleStatus) {
                VehicleStatus.AVAILABLE -> context.getDrawable(R.drawable.ic_vehicle_mark)
                VehicleStatus.RESERVED -> context.getDrawable(R.drawable.ic_vehicle_reserved_mark)
                VehicleStatus.RENTED -> context.getDrawable(R.drawable.ic_vehicle_rented_mark)
                VehicleStatus.RETURNED -> context.getDrawable(R.drawable.ic_vehicle_returned_mark)
                VehicleStatus.UNAVAILABLE -> context.getDrawable(R.drawable.ic_vehicle_unavailable_mark)
            }
}