package com.vo.vozilla.repository.network.mapobjects.models

import com.google.gson.annotations.SerializedName
import com.vo.vozilla.repository.network.mapobjects.models.zone.Zone

class ZonesMapObjectResponse(@SerializedName("zones") val zones: List<Zone>)