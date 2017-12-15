package com.grupotransmares.sagecofuve.home.agenda

import com.grupotransmares.sagecofuve.di.ActivityScope
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
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

    fun updateStatus(visit: Visit) {
        when (visit.status) {
            Visit.STATUS_PENDING -> visit.status = Visit.STATUS_IN_PROGRESS

            Visit.STATUS_IN_PROGRESS -> visit.status = Visit.STATUS_ENDED

            Visit.STATUS_ENDED -> visit.status = Visit.STATUS_PENDING
        }
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