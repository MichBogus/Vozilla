package com.vo.vozilla.mapactivity.presentation.parkingmapfragment.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.MapActivity
import kotlinx.android.synthetic.main.fragment_parking_map.*
import javax.inject.Inject

class ParkingMapFragment : Fragment(), ParkingMapFragmentMVP.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: ParkingMapFragmentMVP.Presenter

    private var googleMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_parking_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MapActivity).getMapActivityComponent().plusParkingMapFragmentComponent().inject(this)

        parking_map.onCreate(savedInstanceState)
        parking_map.onResume()

        parking_map.getMapAsync(this)
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

        presenter.downloadParking()
    }

    override fun showParking(parkingList: List<MarkerOptions>) {
        googleMap?.let { map ->
            parkingList.forEach {
                map.addMarker(it)
            }
        }
    }
}