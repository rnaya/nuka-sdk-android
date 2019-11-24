package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.*

class SentTextChatMessageHolder(itemView: View) : ChatMessageHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        itemView.content.text = chatMessage.text
    }

}