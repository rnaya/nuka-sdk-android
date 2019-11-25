package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ChatMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(chatMessage: ChatMessage)
    abstract fun setViewModel(chatBotViewModel: ChatBotViewModel)
}