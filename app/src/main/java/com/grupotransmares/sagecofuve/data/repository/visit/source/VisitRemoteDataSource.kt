package com.grupotransmares.sagecofuve.data.repository.visit.source

import android.accounts.NetworkErrorException
import android.content.Context
import com.grupotransmares.sagecofuve.data.network.ApiService
import com.grupotransmares.sagecofuve.extentions.hasInternetConnection
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.tracking.domain.TrackKey
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class VisitRemoteDataSource(private val context: Context, private val apiService: ApiService) : VisitDataSource {

    companion object {
        lateinit var VISIT_SERVICE_DATA: MutableList<Visit>
        const val SERVICE_LATENCY_IN_MILLIS = 5000L
    }

    init {
        VISIT_SERVICE_DATA = mutableListOf()

        addVisit(1, "EXPEDITORS", "AV. REPUBLICA DE PANAMA NRO. 3591",
                "18/12/2017 09:00:00", Visit.STATUS_PENDING)
        addVisit(2, "HELLLMAN", "AV. CIRCUNVALACIÓN DEL CLUB GOLF LOS INCAS NRO. 154 INT. 1901 URB. LOTIZACIÓN CLUB GOLF LOS INCAS (EDIFICIO CAPITAL GOLF) ",
                "18/12/2017 10:00:00", Visit.STATUS_PENDING)
        addVisit(3, "IFS", "AV. ALONSO DE MOLINA NRO. 1611 URB. LIMA POLO AND HUNT CLUB (AV.PRIMAVERA 2390) ",
                "18/12/2017 11:00:00", Visit.STATUS_PENDING)
        addVisit(4, "HARTRODT", "AV. REPUBLICA DE PANAMA NRO. 3591",
                "18/12/2017 14:00:00", Visit.STATUS_PENDING)
        addVisit(5, "APRILE", "AV. CIRCUNVALACIÓN DEL CLUB GOLF LOS INCAS NRO. 154 INT. 1901 URB. LOTIZACIÓN CLUB GOLF LOS INCAS (EDIFICIO CAPITAL GOLF) ",
                "18/12/2017 15:00:00", Visit.STATUS_PENDING)
        addVisit(6, "Kn", "AV. ALONSO DE MOLINA NRO. 1611 URB. LIMA POLO AND HUNT CLUB (AV.PRIMAVERA 2390) ",
                "18/12/2017 15:00:00", Visit.STATUS_PENDING)
    }

    private fun addVisit(id: Long, client: String, address: String, dateTime: String, status: String) {
        val visit = Visit(id, client, address, dateTime, status)
        VISIT_SERVICE_DATA.add(visit)
    }

    override fun getVisits(): Single<List<Visit>> {
        return Flowable
                .fromIterable(VISIT_SERVICE_DATA)
                .delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .toList()

    }

//    override fun getVisits(): Single<List<Visit>> {
//         return Single.create({ emitter ->
//             if(context.hasInternetConnection()) {
//                 val response = apiService.getVisits().execute()
//                 if (response.isSuccessful) {
//                     response.body()?.let { emitter.onSuccess(it) }
//                 } else {
//                     emitter.onError(NetworkErrorException())
//                 }
//             } else {
//                 emitter.onError(NetworkErrorException())
//             }
//         })
//    }

    override fun registerVisitLocation(visitId: Long, latitude: Double, longitude: Double): Single<TrackKey> {
        return Single.create({ emitter ->
            if(context.hasInternetConnection()) {
                val requestValues = mapOf("EventId" to visitId, "Lat" to latitude, "lng" to longitude)
                val response = apiService.registerLocation(requestValues).execute()
                if (response.isSuccessful) {
                    response.body()?.let { emitter.onSuccess(it) }
                } else {
                    emitter.onError(NetworkErrorException())
                }
            } else {
                emitter.onError(NetworkErrorException())
            }
        })
    }

    override fun updateVisitLocation(trackKey: String, latitude: Double, longitude: Double): Single<Unit> {
        return Single.create({ emitter ->
            if(context.hasInternetConnection()) {
                val requestValues = mapOf("TrackKey" to trackKey, "Lat" to latitude, "lng" to longitude)
                val response = apiService.updateLocation(requestValues).execute()
                if (response.isSuccessful) {
                    emitter.onSuccess(Unit)
                } else {
                    emitter.onError(NetworkErrorException())
                }
            } else {
                emitter.onError(NetworkErrorException())
            }
        })
    }
}