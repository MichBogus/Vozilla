package com.vo.vozilla

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (map as SupportMapFragment).getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {

            val polygons = mutableListOf<Polygon>()

            val polygon1 = googleMap.addPolygon(PolygonOptions()
                                                        .clickable(true)
                                                        .add(
                                                                LatLng(-31.852, 110.211),
                                                                LatLng(-27.457, 153.040),
                                                                LatLng(-33.852, 151.211),
                                                                LatLng(-37.813, 144.962),
                                                                LatLng(-34.928, 138.599)))
            polygons.add(polygon1)

            for (polygon in polygons) {
                val strokeColor = polygon.strokeColor
                polygon.fillColor = strokeColor
            }

            it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.684, 133.903), 4f))
        }
    }
}
