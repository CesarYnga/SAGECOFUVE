package com.grupotransmares.sagecofuve.common

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase<in P, T> {

    private val disposables = CompositeDisposable()

    var forceUpdate = false

    abstract fun buildUseCaseObservable(requestValues: P? = null): Flowable<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, onComplete: (() -> Unit)? = null, requestValues: P? = null) {
        val flowable = buildUseCaseObservable(requestValues)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread(), true)
        disposables.add(flowable.subscribe(onNext, onError, onComplete))
    }

    fun dispose() = { if (!disposables.isDisposed) disposables.dispose() }
}