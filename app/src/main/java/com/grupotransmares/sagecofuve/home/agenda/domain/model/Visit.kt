package com.grupotransmares.sagecofuve.home.agenda.domain.model

import android.support.annotation.StringDef

data class Visit(val client: String, val address: String, val dateTime: Long, @Status val status: String) {

    companion object {
        const val STATUS_PENDING = "VISITA PENDIENTE"
        const val STATUS_IN_PROGRESS = "EN PROGRESO"
        const val STATUS_ENDED = "FINALIZADA"

        @StringDef(STATUS_PENDING, STATUS_IN_PROGRESS, STATUS_ENDED)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Status
    }
}