package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverter
import io.reactivex.Single
import javax.inject.Inject

class ParkingMapInteractorImpl
@Inject constructor(private val service: ParkingMapObjectService,
                    private val converter: ParkingToMarkerConverter) : ParkingMapInteractor {

    override fun getParking(): Single<List<Pair<ParkingSpace, MarkerOptions>>> =
            service.getParking().map { it.parkings }.map { converter.convert(it) }
}