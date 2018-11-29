package com.vo.vozilla.application.maindi.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier

@Module
class SchedulerModule {

    @Provides
    @SchedulerUI
    fun schedulerUI(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @SchedulerIO
    fun schedulerIO(): Scheduler = Schedulers.io()

    @Provides
    @SchedulerComputation
    fun schedulerComputation(): Scheduler = Schedulers.computation()

    @Qualifier
    annotation class SchedulerUI

    @Qualifier
    annotation class SchedulerIO

    @Qualifier
    annotation class SchedulerComputation
}