package com.grupotransmares.sagecofuve.data.repository.chatbot.source

import ai.api.AIConfiguration
import ai.api.AIServiceException
import ai.api.android.AIDataService
import ai.api.model.AIEvent
import ai.api.model.AIRequest
import android.content.Context
import com.grupotransmares.sagecofuve.data.repository.chatbot.source.ChatbotDataSource
import com.grupotransmares.sagecofuve.exception.NetworkException
import com.grupotransmares.sagecofuve.extentions.hasInternetConnection
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotMessage
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class ChatbotRemoteDataSource(private val context: Context): ChatbotDataSource {

    private val aiDataService: AIDataService

    init {
        val config =  ai.api.android.AIConfiguration("5e339f24954d487ca8dc5a5b6a8dd769",
                AIConfiguration.SupportedLanguages.Spanish,
                ai.api.android.AIConfiguration.RecognitionEngine.System)
        aiDataService = AIDataService(context, config)
    }

    override fun getWelcomeMessage(): Single<ChatbotMessage> {
        return Single.create { emitter ->
            if (context.hasInternetConnection()) {
                try {
                    val request = AIRequest()
                    request.setEvent(AIEvent("welcome"))

                    Timber.tag("Chatbot").d("Event: %s", "welcome")
                    val aiResponse = aiDataService.request(request)

                    val response = aiResponse.result.fulfillment.speech

                    Timber.tag("Chatbot").d("Response: %s", response)

                    emitter.onSuccess(ChatbotMessage(response))
                } catch (e: AIServiceException) {
                    emitter.onError(e)
                }
            } else{
                emitter.onError(NetworkException())
            }
        }
    }

    override fun sendMessage(message: String): Single<ChatbotMessage> {
        return Single.create { emitter ->
            if (context.hasInternetConnection()) {
                try {
                    val request = AIRequest()
                    request.setQuery(message)

                    Timber.tag("Chatbot").d("Query: %s", message)
                    val aiResponse = aiDataService.request(request)

                    val response = aiResponse.result.fulfillment.speech

                    Timber.tag("Chatbot").d("Response: %s", response)

                    emitter.onSuccess(ChatbotMessage(response))
                } catch (e: AIServiceException) {
                    emitter.onError(e)
                }
            } else{
                emitter.onError(NetworkException())
            }
        }
    }


}