package com.grupotransmares.sagecofuve.data.repository.source

import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class VisitRemoteDataStore : VisitDataStore {

    companion object {
        lateinit var VISIT_SERVICE_DATA: MutableList<Visit>
        const val SERVICE_LATENCY_IN_MILLIS = 5000L
    }

    init {
        VISIT_SERVICE_DATA = mutableListOf()

        addVisit("Compañia 1", "Av. Bolivar 1385, Pueblo Libre",
                1513006241, Visit.STATUS_PENDING)
        addVisit("Compañia 1", "Av. Bolivar 1385, Pueblo Libre",
                1513006241, Visit.STATUS_PENDING)
        addVisit("Compañia 1", "Av. Bolivar 1385, Pueblo Libre",
                1513006241, Visit.STATUS_PENDING)
    }

    private fun addVisit(client: String, address: String, dateTime: Long, status: String) {
        val visit = Visit(client, address, dateTime, status)
        VISIT_SERVICE_DATA.add(visit)
    }

    override fun getVisits(): Flowable<List<Visit>> {

        return Flowable
                .fromIterable(VISIT_SERVICE_DATA)
//                .delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .toList()
                .toFlowable()

    }
}