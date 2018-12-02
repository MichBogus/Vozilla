package com.vo.vozilla.repository.network.filter

import com.vo.vozilla.repository.network.filter.models.FiltersResponse
import io.reactivex.Single

interface FilterFeedApi {

    fun getFilters(): Single<FiltersResponse>
}