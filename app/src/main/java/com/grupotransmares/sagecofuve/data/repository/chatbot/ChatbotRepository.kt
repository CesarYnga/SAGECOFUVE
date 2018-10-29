package com.grupotransmares.sagecofuve.data.repository.chatbot

import android.content.Context
import com.grupotransmares.sagecofuve.data.repository.chatbot.source.ChatbotDataSource
import com.grupotransmares.sagecofuve.data.repository.chatbot.sourceif.ChatbotRemoteDataSource
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatbotRepository
@Inject constructor(context: Context) : ChatbotDataSource {

    private val chatbotRemoteDataSource: ChatbotDataSource

    init {
        chatbotRemoteDataSource = ChatbotRemoteDataSource(context)
    }

    override fun getWelcomeMessage(): Single<ChatbotResponse> {
        return chatbotRemoteDataSource.getWelcomeMessage()
    }

    override fun sendMessage(message: String): Single<ChatbotResponse> {
        return chatbotRemoteDataSource.sendMessage(message)
    }
}