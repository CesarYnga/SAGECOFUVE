package com.grupotransmares.sagecofuve.data.repository

import android.content.Context
import com.grupotransmares.sagecofuve.data.network.ApiService
import com.grupotransmares.sagecofuve.data.repository.source.VisitDataStore
import com.grupotransmares.sagecofuve.data.repository.source.VisitRemoteDataStore
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.tracking.domain.TrackKey
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VisitRepository
    @Inject
    constructor(context: Context, apiService: ApiService) : VisitDataStore {

    private val visitRemoteDataSource: VisitDataStore

    init {
        visitRemoteDataSource = VisitRemoteDataStore(context, apiService)
    }

    override fun getVisits(): Single<List<Visit>> {
        return visitRemoteDataSource.getVisits()
    }

    override fun registerVisitLocation(visitId: Long, latitude: Double, longitude: Double): Single<TrackKey> {
        return visitRemoteDataSource.registerVisitLocation(visitId, latitude, longitude)
    }

    override fun updateVisitLocation(trackKey: String, latitude: Double, longitude: Double): Single<Unit> {
        return visitRemoteDataSource.updateVisitLocation(trackKey, latitude, longitude)
    }
}