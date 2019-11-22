package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_message_sent.view.*

class SentChatMessageHolder(itemView: View) : MessageHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        itemView.content.text = chatMessage.text
    }

}