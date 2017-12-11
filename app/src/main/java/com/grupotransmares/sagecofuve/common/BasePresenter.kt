package com.grupotransmares.sagecofuve.common

abstract class BasePresenter<V: BaseView> {

    var view: V? = null

    abstract fun subscribe()

    abstract fun unsubscribe()
}