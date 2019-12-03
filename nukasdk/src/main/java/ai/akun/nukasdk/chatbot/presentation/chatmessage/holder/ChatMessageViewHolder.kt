package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ChatMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(chatMessage: ChatMessage)
}