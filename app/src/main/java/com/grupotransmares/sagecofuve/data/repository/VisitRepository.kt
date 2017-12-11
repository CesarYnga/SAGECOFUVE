package com.grupotransmares.sagecofuve.data.repository

import com.grupotransmares.sagecofuve.data.repository.source.VisitDataStore
import com.grupotransmares.sagecofuve.data.repository.source.VisitRemoteDataStore
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VisitRepository @Inject constructor() : VisitDataStore {

    private val visitRemoteDataSource: VisitDataStore

    init {
        visitRemoteDataSource = VisitRemoteDataStore()
    }

    override fun getVisits(): Flowable<List<Visit>> {
        return visitRemoteDataSource.getVisits()
    }

}