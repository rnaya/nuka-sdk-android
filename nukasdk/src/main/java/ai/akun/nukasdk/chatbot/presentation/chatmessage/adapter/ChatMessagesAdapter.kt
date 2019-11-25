package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageHolder
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatMessagesAdapter : RecyclerView.Adapter<ChatMessageHolder>() {

    private var chatMessages: MutableList<ChatMessage> = mutableListOf()
    private var chatBotViewModel: ChatBotViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChatMessageView.getChatMessageViewHolder(parent, viewType, chatBotViewModel)


    override fun getItemViewType(position: Int) =
        ChatMessageView.getChatMessageViewType(chatMessages[position])

    override fun getItemCount(): Int = chatMessages.size

    override fun onBindViewHolder(holder: ChatMessageHolder, position: Int) {
        holder.bind(chatMessages[position])
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