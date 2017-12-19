package com.grupotransmares.sagecofuve.tracking.domain.usecase

import com.grupotransmares.sagecofuve.common.UseCase
import com.grupotransmares.sagecofuve.data.repository.VisitRepository
import com.grupotransmares.sagecofuve.tracking.domain.TrackKey
import io.reactivex.Flowable
import javax.inject.Inject

class RegisterVisitLocation
    @Inject
    constructor(private val visitRepository: VisitRepository) : UseCase<RegisterVisitLocation.Params, TrackKey>() {

    override fun buildUseCaseObservable(requestValues: Params?): Flowable<TrackKey> {

        val visitId = requestValues?.visitId
        val longitude = requestValues?.longitude
        val latitude = requestValues?.latitude
        return visitRepository.registerVisitLocation(visitId!!, longitude!!, latitude!!).toFlowable()
    }

    data class Params(val visitId: Long, val latitude: Double, val longitude: Double)

}