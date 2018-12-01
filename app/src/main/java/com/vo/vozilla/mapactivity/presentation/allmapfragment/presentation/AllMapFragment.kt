package com.vo.vozilla.mapactivity.presentation.allmapfragment.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.vo.vozilla.R
import com.vo.vozilla.mapactivity.presentation.MapActivity
import kotlinx.android.synthetic.main.fragment_all_map.*

class AllMapFragment : Fragment(), AllMapFragmentMVP.View, OnMapReadyCallback {

//    @Inject
//    lateinit var presenter: AllMapFragmentMVP.Presenter

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
//        presenter.attach(this)
    }

    override fun onStop() {
//        presenter.detach()
        super.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
    }

//    override fun showZones(polygons: List<PolygonOptions>) {
//        googleMap?.let { map ->
//            polygons.forEach {
//                map.addPolygon(it)
//            }
//
//            polygons.first().let {
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.points.first(), 4f))
//            }
//        }
//    }
}