package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.*

class SentTextChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String, ChatMessageIntent) -> Unit)?) {
        itemView.content.text = chatMessage.text
    }

}