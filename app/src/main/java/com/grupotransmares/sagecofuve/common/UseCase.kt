package com.grupotransmares.sagecofuve.common

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCase<in P, T> {

    private val disposables = CompositeDisposable()

    var forceUpdate = false

    abstract fun buildUseCaseObservable(requestValues: P?): Flowable<T>

    fun execute(observer: DisposableSubscriber<T>, requestValues: P? = null) {
        val flowable = buildUseCaseObservable(requestValues)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread(), true)
        disposables.add(flowable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}