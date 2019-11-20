package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessageType
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.MessageHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ReceivedChatMessageHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.SentChatMessageHolder
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatMessagesAdapter : RecyclerView.Adapter<MessageHolder>() {

    private var messages: MutableList<ChatMessage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        return when(viewType) {
            ChatMessageType.RECEIVED.id -> {
                val inflatedView = parent.inflate(R.layout.item_row_message_received, false)
                ReceivedChatMessageHolder(inflatedView)
            }
            else -> {
                val inflatedView = parent.inflate(R.layout.item_row_message_sent, false)
                SentChatMessageHolder(inflatedView)
            }
        }
    }

    override fun getItemViewType(position: Int) = messages[position].type.id

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    fun loadMessages(messages: List<ChatMessage>) {
        this.messages = messages.toMutableList()
        notifyDataSetChanged()
    }

    fun addNewMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

}