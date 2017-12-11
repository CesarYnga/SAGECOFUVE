package com.grupotransmares.sagecofuve.home.agenda

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseFragment
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import kotlinx.android.synthetic.main.fragment_agenda.*
import javax.inject.Inject

class AgendaFragment : BaseFragment(), AgendaContract.View {

    private val adapter = AgendaAdapter()

    @Inject lateinit var presenter: AgendaPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.view = this

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        swipeRefresh.setOnRefreshListener {
            presenter.loadVisits(true)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun showLoadingIndicator(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

    override fun showVisits(visits: List<Visit>) {
        adapter.setVisits(visits)
    }
}