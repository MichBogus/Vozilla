package com.vo.vozilla.mapactivity.presentation.allmapfragment.domain

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.ZoneToPolygonConverter
import com.vo.vozilla.mapactivity.presentation.parkingmapfragment.domain.ParkingSpace
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import io.reactivex.Single
import javax.inject.Inject

class AllMapFragmentInteractorImpl
@Inject constructor(private val service: AllMapObjectsService,
                    private val zoneConverter: ZoneToPolygonConverter,
                    private val vehicleConverter: VehicleToMarkerConverter,
                    private val parkingConverter: ParkingToMarkerConverter) : AllMapFragmentInteractor {

    override fun getParking(): Single<List<Pair<ParkingSpace, MarkerOptions>>> =
            service.getParking().map { it.parkings }.map { parkingConverter.convert(it) }

    override fun getVehicles(): Single<List<Pair<VehicleStatus, MarkerOptions>>> =
            service.getVehicles().map { it.vehicles }.map { vehicleConverter.convert(it) }

    override fun getZonesAsPolygons(): Single<List<PolygonOptions>> =
            service.getZones().map { it.zones }.map { zoneConverter.convert(it) }
}