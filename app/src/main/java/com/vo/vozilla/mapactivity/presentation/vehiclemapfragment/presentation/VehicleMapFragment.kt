package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.ui.IconGenerator
import com.vo.vozilla.R
import com.vo.vozilla.dialog.SingleChoiceDialog
import com.vo.vozilla.mapactivity.domain.VehicleDomainModel
import com.vo.vozilla.mapactivity.presentation.MapActivity
import com.vo.vozilla.mapactivity.presentation.converters.VehicleToMarkerConverter
import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.presentation.vehicleinfodialog.VehicleInfoDialog
import kotlinx.android.synthetic.main.fragment_vehicle_map.*
import javax.inject.Inject


class VehicleMapFragment : Fragment(), VehicleMapFragmentMVP.View, OnMapReadyCallback, OnMarkerClickListener {

    @Inject
    lateinit var presenter: VehicleMapFragmentMVP.Presenter

    @Inject
    lateinit var converter: VehicleToMarkerConverter

    private var googleMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_vehicle_map, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        this.googleMap!!.setOnMarkerClickListener(this)

        presenter.downloadVehicles()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.vehicle_filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_no_filters -> presenter.downloadVehicles()
            R.id.menu_filter_by_model ->
                presenter.getVehicleModelFilters().apply {
                    openVehicleModelDialog(this.keys, this.values)
                }
            R.id.menu_filter_by_status ->
                presenter.getVehicleStatusFilters().apply {
                    openVehicleStatusDialog(this.keys, this.values)
                }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openVehicleModelDialog(keys: Set<String>,
                                       values: Collection<String>) {
        SingleChoiceDialog.create(keys.toTypedArray(), values.toTypedArray())
                .show(activity!!.supportFragmentManager, "FilterTag")
    }

    private fun openVehicleStatusDialog(keys: Set<String>,
                                        values: Collection<String>) {
        SingleChoiceDialog.create(keys.toTypedArray(), values.toTypedArray())
                .show(activity!!.supportFragmentManager, "FilterTag")
    }

    override fun clearMap() {
        googleMap?.clear()
    }

    override fun showVehicles(vehicleList: List<VehicleDomainModel>) {
        googleMap?.let { map ->
            vehicleList.forEach {
                val iconGenerator = IconGenerator(context).apply {
                    setBackground(context?.getDrawable(converter.getMarkerDrawableByVehicleStatus(it.vehicleStatus)))
                }
                val iconBitmap = iconGenerator.makeIcon()
                it.markerOption.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))

                val marker = map.addMarker(it.markerOption)
                marker.tag = it.dataModel
            }

            vehicleList.first().let {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.markerOption.position, 4f))
            }
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let {
            VehicleInfoDialog.create(it.tag as MutableMap<String, Any>)
                    .show(activity!!.supportFragmentManager, "VehicleInfoDialogTag")
        }
        return false
    }
}