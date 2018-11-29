package com.vo.vozilla.mapactivity.presentation

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.PolygonOptions
import com.vo.vozilla.R
import com.vo.vozilla.baseactivity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MapActivity : BaseActivity(), MapActivityMVP.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: MapActivityMVP.Presenter

    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vozillaMainComponent().plusMapActivityComponent().inject(this)

        (map as SupportMapFragment).getMapAsync(this)

        presenter.attach(this)
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
