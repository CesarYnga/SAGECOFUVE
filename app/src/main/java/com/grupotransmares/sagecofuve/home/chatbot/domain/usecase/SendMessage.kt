package com.grupotransmares.sagecofuve.home.chatbot.domain.usecase

import com.grupotransmares.sagecofuve.common.UseCase
import com.grupotransmares.sagecofuve.data.repository.chatbot.ChatbotRepository
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse
import io.reactivex.Flowable
import javax.inject.Inject

class SendMessage
@Inject constructor(private val chatbotRepository: ChatbotRepository) : UseCase<String, ChatbotResponse>() {

    override fun buildUseCaseObservable(requestValues: String?): Flowable<ChatbotResponse> {
        return chatbotRepository.sendMessage(requestValues!!).toFlowable()
    }
}