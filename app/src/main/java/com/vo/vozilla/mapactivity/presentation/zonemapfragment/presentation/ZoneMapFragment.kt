package com.vo.vozilla.mapactivity.presentation.zonemapfragment.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.MapActivity
import kotlinx.android.synthetic.main.fragment_zone_map.*
import javax.inject.Inject

class ZoneMapFragment : Fragment(), ZoneMapFragmentMVP.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: ZoneMapFragmentMVP.Presenter

    private var googleMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_zone_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MapActivity).getMapActivityComponent().plusZoneMapFragmentComponent().inject(this)

        zone_map.onCreate(savedInstanceState)
        zone_map.onResume()

        zone_map.getMapAsync(this)
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

        presenter.downloadZones()
    }

    override fun showZones(polygons: List<PolygonOptions>) {
        googleMap?.let { map ->
            polygons.forEach {
                map.addPolygon(it)
            }
        }
    }
}