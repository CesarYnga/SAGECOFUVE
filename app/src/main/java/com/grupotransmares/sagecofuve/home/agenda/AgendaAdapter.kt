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

    private var visits: List<Visit>? = null

    var onVisitClickListener: OnVisitClickListener? = null

    override fun getItemCount(): Int {
        return visits?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_visit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        visits?.get(position)?.let { holder?.bind(it) }
    }

    fun setVisits(visits: List<Visit>) {
        this.visits = visits
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        init {
            containerView?.setOnClickListener {
                visits?.get(adapterPosition)?.let {
                    onVisitClickListener?.onVisitClick(it)
                    notifyItemChanged(adapterPosition)
                }
            }
        }

        fun bind(visit: Visit) {
            txtClient.text = visit.client
            txtAddress.text = visit.address
            txtDateTime.text = visit.dateTime.toString()
            txtStatus.text = visit.status
        }
    }

    interface OnVisitClickListener {
        fun onVisitClick(visit: Visit)
    }
}