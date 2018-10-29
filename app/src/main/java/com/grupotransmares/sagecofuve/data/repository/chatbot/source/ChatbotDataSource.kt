package com.grupotransmares.sagecofuve.data.repository.chatbot.source

import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse
import io.reactivex.Single

interface ChatbotDataSource {

    fun getWelcomeMessage(): Single<ChatbotResponse>

    fun sendMessage(message: String): Single<ChatbotResponse>
}