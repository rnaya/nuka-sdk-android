package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageViewHolder
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageType
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatMessagesAdapter : RecyclerView.Adapter<ChatMessageViewHolder>() {

    private var chatMessages: MutableList<ChatMessage> = mutableListOf()
    private var chatBotViewModel: ChatBotViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val chatMessageType = ChatMessageType.values().find { it.getViewType() == viewType }
        return chatMessageType?.getViewHolder(parent, chatBotViewModel)
            ?: ChatMessageType.RECEIVED_TEXT.getViewHolder(parent, chatBotViewModel)
    }

    override fun getItemViewType(position: Int) = chatMessages[position].type.getViewType()

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

    fun setViewModel(chatBotViewModel: ChatBotViewModel) {
        this.chatBotViewModel = chatBotViewModel
    }

}