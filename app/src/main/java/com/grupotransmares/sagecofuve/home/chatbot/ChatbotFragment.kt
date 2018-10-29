package com.grupotransmares.sagecofuve.home.chatbot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseFragment
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotResponse
import kotlinx.android.synthetic.main.fragment_chatbot.*
import javax.inject.Inject


class ChatbotFragment : BaseFragment(), ChatbotContract.View {

    @Inject lateinit var presenter: ChatbotPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chatbot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.view = this

        if(savedInstanceState == null) {
            presenter.subscribe()
        }

        fabSend.setOnClickListener {
            presenter.sendMessage(edtMessage.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showChatbotResponse(chatbotResponse: ChatbotResponse) {

    }
}