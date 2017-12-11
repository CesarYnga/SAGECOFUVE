package com.grupotransmares.sagecofuve.home.agenda.domain.usecase

import com.grupotransmares.sagecofuve.common.UseCase
import com.grupotransmares.sagecofuve.data.repository.VisitRepository
import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import io.reactivex.Flowable
import javax.inject.Inject

class GetVisits @Inject constructor(private val visitRepository: VisitRepository) : UseCase<Unit, List<Visit>>() {

    override fun buildUseCaseObservable(requestValues: Unit?): Flowable<List<Visit>> {
        return visitRepository.getVisits()
    }

}