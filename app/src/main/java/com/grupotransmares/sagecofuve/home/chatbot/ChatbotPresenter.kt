package com.grupotransmares.sagecofuve.home.chatbot

import com.grupotransmares.sagecofuve.di.ActivityScope
import com.grupotransmares.sagecofuve.home.chatbot.domain.usecase.GetWelcomeMessage
import com.grupotransmares.sagecofuve.home.chatbot.domain.usecase.SendMessage
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class ChatbotPresenter
@Inject constructor(private val getWelcomeMessage: GetWelcomeMessage,
                    private val sendMessage: SendMessage) : ChatbotContract.Presenter() {

    override fun subscribe() {
        getWelcomeMessage()
    }

    override fun unsubscribe() {
        getWelcomeMessage.dispose()
        sendMessage.dispose()
        view = null
    }

    override fun getWelcomeMessage() {
        getWelcomeMessage.execute(onNext = {chatbotMessage ->
            view?.showChatbotMessage(chatbotMessage)
        }, onError = {e ->
            Timber.e(e, "Error getting welcome message")
        })
    }

    override fun sendMessage(message: String) {
        sendMessage.execute(onNext = {chatbotResponse ->
            view?.showChatbotMessage(chatbotResponse)
        }, onError = {e ->
            Timber.e(e, "Error sending message")
        }, requestValues = message)
    }
}