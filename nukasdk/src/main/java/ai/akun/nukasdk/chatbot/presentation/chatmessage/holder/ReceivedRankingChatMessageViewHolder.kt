package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content

class ReceivedRankingChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String) -> Unit)?) {
        itemView.content.text = "Received info about ${chatMessage.webhookPayload?.rankings?.count()} teams"
    }
}