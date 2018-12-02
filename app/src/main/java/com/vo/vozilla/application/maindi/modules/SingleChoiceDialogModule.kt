package com.vo.vozilla.application.maindi.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Qualifier

@Module
class SingleChoiceDialogModule {

    private val itemPicked = PublishSubject.create<Pair<String, String>>()

    @Provides
    @SingleChoiceItemPicked
    fun itemPickedObserver(): Subject<Pair<String, String>> {
        return itemPicked
    }

    @Provides
    @SingleChoiceItemPicked
    fun itemPickedObservable(): Observable<Pair<String, String>> {
        return itemPicked.hide()
    }


    @Qualifier
    annotation class SingleChoiceItemPicked
}