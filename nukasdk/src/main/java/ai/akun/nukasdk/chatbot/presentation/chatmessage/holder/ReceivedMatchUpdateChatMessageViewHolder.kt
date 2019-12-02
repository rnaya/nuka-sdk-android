package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import kotlinx.android.synthetic.main.item_row_match_update.view.*

class ReceivedMatchUpdateChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    private lateinit var onSendNewMessage: ((String, ChatMessageIntent) -> Unit)

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String, ChatMessageIntent) -> Unit)?) {
        this.onSendNewMessage = onSendNewMessage!!
        itemView.content.text = chatMessage.text
    }

}