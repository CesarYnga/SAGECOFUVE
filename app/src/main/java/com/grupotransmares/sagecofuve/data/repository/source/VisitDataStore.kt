package com.grupotransmares.sagecofuve.data.repository.source

import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import io.reactivex.Flowable

interface VisitDataStore {

    fun getVisits(): Flowable<List<Visit>>
}