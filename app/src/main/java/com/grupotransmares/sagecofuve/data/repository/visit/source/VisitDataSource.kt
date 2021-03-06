package com.grupotransmares.sagecofuve.data.repository.visit.source

import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.tracking.domain.TrackKey
import io.reactivex.Single

interface VisitDataSource {

    fun getVisits(): Single<List<Visit>>

    fun registerVisitLocation(visitId: Long, latitude: Double, longitude: Double): Single<TrackKey>

    fun updateVisitLocation(trackKey: String, latitude: Double, longitude: Double): Single<Unit>
}