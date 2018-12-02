package com.vo.vozilla.repository.network.di

import com.vo.vozilla.repository.network.filter.FilterFeed
import com.vo.vozilla.repository.network.filter.FilterFeedApi
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeed
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import dagger.Binds
import dagger.Module

@Module
abstract class FeedApiModule {

    @Binds
    abstract fun bindMapObjectsFeed(feed: MapObjectsFeed): MapObjectsFeedApi

    @Binds
    abstract fun bindFilterFeed(feed: FilterFeed): FilterFeedApi
}