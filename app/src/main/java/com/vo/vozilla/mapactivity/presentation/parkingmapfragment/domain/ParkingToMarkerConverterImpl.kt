package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import android.content.Context
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import com.vo.vozilla.R
import com.vo.vozilla.repository.network.mapobjects.models.parking.Parking
import javax.inject.Inject

class ParkingToMarkerConverterImpl
@Inject constructor(private val context: Context) : ParkingToMarkerConverter {

    override fun convert(parking: List<Parking>): List<MarkerOptions> {
        val markersList = mutableListOf<MarkerOptions>()
        parking.forEach {
            val iconGenerator = IconGenerator(context).apply {
                setBackground(getPinBackgroundByParkingSpace(it.availableSpacesCount, it.spacesCount))
            }
            val iconBitmap = iconGenerator.makeIcon("${it.availableSpacesCount}/${it.spacesCount}")

            markersList.add(MarkerOptions().position(LatLng(it.location.latitude, it.location.longitude))
                                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
            )
        }
        return markersList
    }

    private fun getPinBackgroundByParkingSpace(availableSpace: Int, spaceCount: Int) =
            if (availableSpace == spaceCount) {
                context.getDrawable(R.drawable.ic_parking_no_more_space)
            } else {
                context.getDrawable(R.drawable.ic_parking_space_left)
            }
}