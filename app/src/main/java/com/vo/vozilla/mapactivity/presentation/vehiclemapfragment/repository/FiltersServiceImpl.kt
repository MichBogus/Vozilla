package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.repository

import com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain.FiltersService
import com.vo.vozilla.repository.network.filter.FilterFeedApi
import com.vo.vozilla.repository.network.filter.models.FiltersResponse
import io.reactivex.Single
import javax.inject.Inject

class FiltersServiceImpl
@Inject constructor(private val filtersFeed: FilterFeedApi) : FiltersService {

    override fun getFilters(): Single<FiltersResponse> =
            filtersFeed.getFilters()
}