package com.grupotransmares.sagecofuve.data.repository.chatbot.source

import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotMessage
import io.reactivex.Single

interface ChatbotDataSource {

    fun getWelcomeMessage(): Single<ChatbotMessage>

    fun sendMessage(message: String): Single<ChatbotMessage>
}