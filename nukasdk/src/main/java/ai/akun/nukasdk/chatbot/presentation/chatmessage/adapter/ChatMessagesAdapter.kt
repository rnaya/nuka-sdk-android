package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageViewHolder
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatMessagesAdapter : RecyclerView.Adapter<ChatMessageViewHolder>() {

    private var chatMessages: MutableList<ChatMessage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val chatMessageType = ChatMessageIntent.values().find { it.getViewType() == viewType }
        return chatMessageType?.getViewHolder(parent)
            ?: ChatMessageIntent.RECEIVED_TEXT.getViewHolder(parent)
    }

    override fun getItemViewType(position: Int) = chatMessages[position].intent.getViewType()

    override fun getItemCount(): Int = chatMessages.size

    override fun onBindViewHolder(viewHolder: ChatMessageViewHolder, position: Int) {
        viewHolder.bind(chatMessages[position])
    }

    fun loadMessages(messages: List<ChatMessage>) {
        if(this.chatMessages.isEmpty()) {
            this.chatMessages = messages.toMutableList()
            notifyDataSetChanged()
        } else {
            addNewMessage(messages.last())
        }
    }

    private fun addNewMessage(message: ChatMessage) {
        chatMessages.add(message)
        notifyItemInserted(chatMessages.size - 1)
    }

}