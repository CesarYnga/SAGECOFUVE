package com.grupotransmares.sagecofuve.home.agenda

import com.grupotransmares.sagecofuve.common.BasePresenter
import com.grupotransmares.sagecofuve.common.BaseView
import com.grupotransmares.sagecofuve.common.LoadingView
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit

interface AgendaContract {

    interface View : LoadingView {
        fun showVisits(visits: List<Visit>)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadVisits(forceUpdate: Boolean)
    }
}