package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

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
import com.google.maps.android.ui.IconGenerator
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.MapActivity
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.VehicleToMarkerConverter
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.VehicleStatus
import kotlinx.android.synthetic.main.fragment_vehicle_map.*
import javax.inject.Inject

class VehicleMapFragment : Fragment(), VehicleMapFragmentMVP.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: VehicleMapFragmentMVP.Presenter

    @Inject
    lateinit var converter: VehicleToMarkerConverter

    private var googleMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_vehicle_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MapActivity).getMapActivityComponent().plusVehicleMapFragmentComponent().inject(this)

        vehicle_map.onCreate(savedInstanceState)
        vehicle_map.onResume()

        vehicle_map.getMapAsync(this)
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

        presenter.downloadVehicles()
    }

    override fun showVehicles(vehicleList: List<Pair<VehicleStatus, MarkerOptions>>) {
        googleMap?.let { map ->
            vehicleList.forEach {
                val iconGenerator = IconGenerator(context).apply {
                    setBackground(context?.getDrawable(converter.getMarkerDrawableByVehicleStatus(it.first)))
                }
                val iconBitmap = iconGenerator.makeIcon()
                it.second.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
                map.addMarker(it.second)
            }

            vehicleList.first().let {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.second.position, 4f))
            }
        }
    }
}