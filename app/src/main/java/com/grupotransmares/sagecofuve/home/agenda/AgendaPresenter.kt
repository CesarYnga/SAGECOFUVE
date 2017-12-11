package com.grupotransmares.sagecofuve.home.agenda

import com.grupotransmares.sagecofuve.di.ActivityScope
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.home.agenda.domain.usecase.GetVisits
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class AgendaPresenter @Inject constructor(private val getVisits: GetVisits) : AgendaContract.Presenter() {

    override fun subscribe() {
        loadVisits(false)
    }

    override fun unsubscribe() {
        getVisits.dispose()
    }

    override fun loadVisits(forceUpdate: Boolean) {
        view?.showLoadingIndicator(true)
        getVisits.forceUpdate = forceUpdate
        getVisits.execute(object : DisposableSubscriber<List<Visit>>() {
            override fun onNext(visits: List<Visit>) {
                view?.showVisits(visits)
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "Error loading visits")
                view?.showLoadingIndicator(false)
            }

            override fun onComplete() {
                view?.showLoadingIndicator(false)
            }
        })
    }
}