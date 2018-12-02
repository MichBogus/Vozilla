package com.vo.vozilla.repository.network.filter

import com.vo.vozilla.repository.network.base.BaseService
import com.vo.vozilla.repository.network.filter.models.FiltersResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import javax.inject.Inject

class FilterFeed
@Inject constructor(okHttpClient: OkHttpClient) : BaseService<FilterRetrofitApi>(okHttpClient), FilterFeedApi {

    override fun getGenericParameter(): Class<FilterRetrofitApi> = FilterRetrofitApi::class.java

    override fun getFilters(): Single<FiltersResponse> =
            restAdapter().getFilters()
}