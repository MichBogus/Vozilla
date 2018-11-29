package com.vo.vozilla.repository.network.di

import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeed
import com.vo.vozilla.repository.network.mapobjects.MapObjectsFeedApi
import dagger.Binds
import dagger.Module

@Module
abstract class FeedApiModule {

    @Binds
    abstract fun bindMapObjectsFeed(feed: MapObjectsFeed): MapObjectsFeedApi
}