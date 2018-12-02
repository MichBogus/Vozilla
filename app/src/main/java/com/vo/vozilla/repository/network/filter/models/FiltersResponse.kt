package com.vo.vozilla.repository.network.filter.models

import com.google.gson.annotations.SerializedName

data class FiltersResponse(@SerializedName("filters") val filters: Filters)