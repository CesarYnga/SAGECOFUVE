package com.grupotransmares.sagecofuve.home.agenda

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_visit.*

class AgendaAdapter : RecyclerView.Adapter<AgendaAdapter.ViewHolder>() {

    private var visits = listOf<Visit>()

    override fun getItemCount(): Int {
        return visits.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_visit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(visits[position])
    }

    fun setVisits(visits: List<Visit>) {
        this.visits = visits
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(visit: Visit) {
            txtClient.text = visit.client
            txtAddress.text = visit.address
            txtDateTime.text = visit.dateTime.toString()
            txtStatus.text = visit.status
        }
    }
}