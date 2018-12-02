package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.maps.android.ui.IconGenerator
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.MapActivity
import com.vo.vozilla.mapactivity.presentation.converters.ParkingToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.mapactivity.domain.ParkingSpace
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import kotlinx.android.synthetic.main.fragment_all_map.*
import javax.inject.Inject

class AllMapFragment : Fragment(), AllMapFragmentMVP.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: AllMapFragmentMVP.Presenter

    @Inject
    lateinit var parkingConverter: ParkingToMarkerConverter

    @Inject
    lateinit var vehicleConverter: VehicleToMarkerConverter

    private var googleMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_all_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MapActivity).getMapActivityComponent().plusAllMapFragmentComponent().inject(this)

        all_map.onCreate(savedInstanceState)
        all_map.onResume()

        all_map.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        presenter.detach()
        super.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap

        presenter.downloadFullMap()
    }

    override fun showZones(polygons: List<PolygonOptions>) {
        googleMap?.let { map ->
            polygons.forEach {
                map.addPolygon(it)
            }

            polygons.first().let {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.points.first(), 4f))
            }
        }
    }

    override fun showVehicles(vehicles: List<VehicleDomainModel>) {
        googleMap?.let { map ->
            vehicles.forEach {
                val iconGenerator = IconGenerator(context).apply {
                    setBackground(context?.getDrawable(vehicleConverter.getMarkerDrawableByVehicleStatus(it.vehicleStatus)))
                }
                val iconBitmap = iconGenerator.makeIcon()
                it.markerOption.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
                map.addMarker(it.markerOption)
            }

            vehicles.first().let {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.markerOption.position, 4f))
            }
        }
    }

    override fun showParking(parkingList: List<Pair<ParkingSpace, MarkerOptions>>) {
        googleMap?.let { map ->
            parkingList.forEach {
                val iconGenerator = IconGenerator(context).apply {
                    setBackground(context?.getDrawable(parkingConverter.getPinBackgroundByParkingSpace(it.first)))
                }
                val iconBitmap = iconGenerator.makeIcon("${it.first.availableSpace}/${it.first.spaceCount}")
                it.second.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
                map.addMarker(it.second)
            }

            parkingList.first().let {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.second.position, 4f))
            }
        }
    }
}