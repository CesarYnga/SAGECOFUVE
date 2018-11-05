package com.grupotransmares.sagecofuve.home.chatbot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseFragment
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotMessage
import kotlinx.android.synthetic.main.fragment_chatbot.*
import javax.inject.Inject

class ChatbotFragment : BaseFragment(), ChatbotContract.View {

    @Inject
    lateinit var presenter: ChatbotPresenter

    private val adapter = ChatbotAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chatbot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.view = this

        if (savedInstanceState == null) {
            presenter.subscribe()
        }

        initUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    fun initUI() {
        val dividerDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerDecoration)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        fabSend.setOnClickListener {
            presenter.sendMessage(edtMessage.text.toString())
        }
    }

    override fun showChatbotMessage(chatbotMessage: ChatbotMessage) {
        adapter.addMessage(chatbotMessage)
    }
}