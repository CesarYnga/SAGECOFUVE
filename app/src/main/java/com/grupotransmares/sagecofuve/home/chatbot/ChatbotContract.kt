package com.grupotransmares.sagecofuve.home.chatbot

import com.grupotransmares.sagecofuve.common.BasePresenter
import com.grupotransmares.sagecofuve.common.BaseView
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse

interface ChatbotContract {

    interface View : BaseView {
        fun showChatbotResponse(chatbotResponse: ChatbotResponse)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getWelcomeMessage()
        abstract fun sendMessage(message: String)
    }
}