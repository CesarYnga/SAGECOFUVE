package com.grupotransmares.sagecofuve.home.chatbot.domain.usecase

import com.grupotransmares.sagecofuve.common.UseCase
import com.grupotransmares.sagecofuve.data.repository.chatbot.ChatbotRepository
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse
import io.reactivex.Flowable
import javax.inject.Inject

class GetWelcomeMessage
@Inject constructor(private val chatbotRepository: ChatbotRepository) : UseCase<Unit, ChatbotResponse>() {

    override fun buildUseCaseObservable(requestValues: Unit?): Flowable<ChatbotResponse> {
        return chatbotRepository.getWelcomeMessage().toFlowable()
    }
}