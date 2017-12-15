package com.grupotransmares.sagecofuve.home.agenda

import com.grupotransmares.sagecofuve.di.ActivityScope
import com.grupotransmares.sagecofuve.home.agenda.domain.usecase.GetVisits
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class AgendaPresenter @Inject constructor(private val getVisits: GetVisits) : AgendaContract.Presenter() {

    override fun subscribe() {
        loadVisits(false)
    }

    override fun unsubscribe() {
        getVisits.dispose()
        view = null
    }

    override fun loadVisits(forceUpdate: Boolean) {
        view?.showLoadingIndicator(true)
        getVisits.forceUpdate = forceUpdate
        getVisits.execute(
                { visits ->
                    view?.showVisits(visits)
                },
                { e ->
                    Timber.e(e, "Error loading visits")
                    view?.showLoadingIndicator(false)
                },
                {
                    view?.showLoadingIndicator(false)
                }
        )
    }
}