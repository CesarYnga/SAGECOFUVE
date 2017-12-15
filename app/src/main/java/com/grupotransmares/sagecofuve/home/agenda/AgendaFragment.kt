package com.grupotransmares.sagecofuve.home.agenda

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseFragment
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.tracking.TrackingService
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.android.synthetic.main.fragment_agenda.*
import timber.log.Timber
import javax.inject.Inject


class AgendaFragment : BaseFragment(), AgendaContract.View {

    private val adapter = AgendaAdapter()

    @Inject lateinit var presenter: AgendaPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.view = this

        initUI()

        if (savedInstanceState == null) {
            presenter.subscribe()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    fun initUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        swipeRefresh.setOnRefreshListener {
            Timber.d("onRefresh")
            presenter.loadVisits(true)
        }

        adapter.onVisitClickListener = object : AgendaAdapter.OnVisitClickListener {
            override fun onVisitClick(visit: Visit) {
                Timber.d("onVisitClick: " + visit)
                when (visit.status) {
                    Visit.STATUS_PENDING -> {
                        startTrackingService(visit)
                    }

                    Visit.STATUS_IN_PROGRESS -> {
                        stopTrackingService(visit)
                    }

                    Visit.STATUS_ENDED -> {
                        presenter.updateStatus(visit)
                    }
                }

            }

        }
    }

    override fun showLoadingIndicator(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

    override fun showVisits(visits: List<Visit>) {
        adapter.setVisits(visits)
    }

    fun startTrackingService(visit: Visit) {
        Timber.d("startTrackingService")
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : BasePermissionListener() {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        Timber.d("onPermissionGranted")
                        presenter.updateStatus(visit)

                        val intent = Intent(context, TrackingService::class.java)
                        activity.startService(intent)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Timber.d("onPermissionDenied")
                        AlertDialog.Builder(context)
                                .setTitle("Location permission")
                                .setMessage("Location permission is needed for this application.")
                                .setPositiveButton("OK", null)
                                .show()
                    }
                })
                .check()
    }

    fun stopTrackingService(visit: Visit) {
        Timber.d("stopTrackingService")
        presenter.updateStatus(visit)
        val intent = Intent(context, TrackingService::class.java)
        activity.stopService(intent)
    }
}