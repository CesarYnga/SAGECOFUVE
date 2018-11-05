package com.grupotransmares.sagecofuve.home.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.home.chatbot.domain.model.ChatbotMessage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_chatbot.*

class ChatbotAdapter : RecyclerView.Adapter<ChatbotAdapter.ViewHolder>() {

    private var chatbotMessages: MutableList<ChatbotMessage>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_chatbot, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatbotMessages?.size ?: 0
    }

    fun setMessages(chatbotMessages: MutableList<ChatbotMessage>?) {
        this.chatbotMessages = chatbotMessages
        notifyDataSetChanged()
    }

    fun addMessage(chatbotMessage: ChatbotMessage) {
        if (chatbotMessages == null) {
            chatbotMessages = mutableListOf()
        }
        chatbotMessages?.let {
            it.add(chatbotMessage)
            notifyItemInserted(it.size - 1)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        chatbotMessages?.get(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(chatbotMessage: ChatbotMessage) {
            txtChatbotMessage.text = chatbotMessage.response
        }
    }
}