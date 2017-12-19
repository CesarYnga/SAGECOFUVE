package com.grupotransmares.sagecofuve.tracking.domain.usecase

import com.grupotransmares.sagecofuve.common.UseCase
import com.grupotransmares.sagecofuve.data.repository.VisitRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UpdateVisitLocation
    @Inject
    constructor(private val visitRepository: VisitRepository) : UseCase<UpdateVisitLocation.Params, Unit>() {

    override fun buildUseCaseObservable(requestValues: Params?): Flowable<Unit> {
        val trackKey = requestValues?.trackKey
        val longitude = requestValues?.longitude
        val latitude = requestValues?.latitude
        return visitRepository.updateVisitLocation(trackKey!!, longitude!!, latitude!!).toFlowable()
    }

    data class Params(val trackKey: String, val latitude: Double, val longitude: Double)

}