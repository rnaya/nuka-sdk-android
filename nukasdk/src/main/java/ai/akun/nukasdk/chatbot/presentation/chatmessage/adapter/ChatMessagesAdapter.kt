package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageReceivedHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.AudioChatMessageSentHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.TextChatMessageSentHolder
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatMessagesAdapter : RecyclerView.Adapter<ChatMessageHolder>() {

    private var chatMessages: MutableList<ChatMessage> = mutableListOf()

    //TODO simplify type checking
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageHolder {
        return when(viewType) {
            ViewType.TEXT_SENT.id -> {
                val inflatedView = parent.inflate(R.layout.item_row_text_chat_message_sent, false)
                TextChatMessageSentHolder(inflatedView)
            }
            ViewType.AUDIO_SENT.id -> {
                val inflatedView = parent.inflate(R.layout.item_row_audio_chat_message_sent, false)
                AudioChatMessageSentHolder(inflatedView)
            }
            ViewType.RECEIVED.id -> {
                val inflatedView = parent.inflate(R.layout.item_row_chat_message_received, false)
                ChatMessageReceivedHolder(inflatedView)
            }
            else -> {
                val inflatedView = parent.inflate(R.layout.item_row_text_chat_message_sent, false)
                TextChatMessageSentHolder(inflatedView)
            }
        }
    }

    override fun getItemViewType(position: Int) : Int{
        val chatMessage = chatMessages[position]
        return if(chatMessage.sent && chatMessage.text?.isNotEmpty() == true)
            ViewType.TEXT_SENT.id
        else if (chatMessage.sent && chatMessage.audioFilePath?.isNotEmpty() == true)
            ViewType.AUDIO_SENT.id
        else
            ViewType.RECEIVED.id
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun onBindViewHolder(holder: ChatMessageHolder, position: Int) {
        val message = chatMessages[position]
        holder.bind(message)
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

enum class ViewType(val id: Int) {
    TEXT_SENT(1),
    AUDIO_SENT(2),
    RECEIVED(3)
}