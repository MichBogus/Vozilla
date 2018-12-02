package com.vo.vozilla.mapactivity.presentation.vehicleinfodialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vo.vozilla.R
import com.vo.vozilla.repository.network.mapobjects.models.vehicle.Vehicle
import config.ATTACHMENT_URL
import kotlinx.android.synthetic.main.vehicle_info_dialog.*

class VehicleInfoDialog : DialogFragment() {

    companion object {

        fun create(dataModel: MutableMap<String, Any>) =
                VehicleInfoDialog().apply {
                    arguments = Bundle().apply {
                        putString(Vehicle.ID, dataModel[Vehicle.ID] as String)
                        putString(Vehicle.NAME, dataModel[Vehicle.NAME] as String)
                        putString(Vehicle.PLATES_NUMBER, dataModel[Vehicle.PLATES_NUMBER] as String)
                        putString(Vehicle.SIDE_NUMBER, dataModel[Vehicle.SIDE_NUMBER] as String)
                        putString(Vehicle.STATUS, dataModel[Vehicle.STATUS] as String)
                        putString(Vehicle.LOCATION_DESCRIPTION, dataModel[Vehicle.LOCATION_DESCRIPTION] as String?)
                        putString(Vehicle.PICTURE_ID, dataModel[Vehicle.PICTURE_ID] as String)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.vehicle_info_dialog, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(this)
                .load("$ATTACHMENT_URL${arguments!!.getString(Vehicle.PICTURE_ID)}")
                .into(vehicle_picture)

        vehicle_name.text = arguments!!.getString(Vehicle.NAME)
        vehicle_plates_number.text = arguments!!.getString(Vehicle.PLATES_NUMBER)
        vehicle_side_number.text = arguments!!.getString(Vehicle.SIDE_NUMBER)
        vehicle_status_name.text = arguments!!.getString(Vehicle.STATUS)
        vehicle_location_description.text = arguments!!.getString(Vehicle.LOCATION_DESCRIPTION)
    }
}