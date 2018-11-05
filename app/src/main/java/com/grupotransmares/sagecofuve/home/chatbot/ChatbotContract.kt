package com.grupotransmares.sagecofuve.home.chatbot

import com.grupotransmares.sagecofuve.common.BasePresenter
import com.grupotransmares.sagecofuve.common.BaseView
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotMessage

interface ChatbotContract {

    interface View : BaseView {
        fun showChatbotMessage(chatbotMessage: ChatbotMessage)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getWelcomeMessage()
        abstract fun sendMessage(message: String)
    }
}