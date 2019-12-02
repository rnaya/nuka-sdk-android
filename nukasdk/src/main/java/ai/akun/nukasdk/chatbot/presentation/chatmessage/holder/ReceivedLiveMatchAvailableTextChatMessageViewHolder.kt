package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import kotlinx.android.synthetic.main.item_row_received_live_match_available_text_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content

class ReceivedLiveMatchAvailableTextChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    private lateinit var onSendNewMessage: ((String, ChatMessageIntent) -> Unit)

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String, ChatMessageIntent) -> Unit)?) {
        this.onSendNewMessage = onSendNewMessage!!
        itemView.content.text = chatMessage.text

        itemView.yesAction.setOnClickListener {
            addNewActionChatMessage("Ok")
        }

        itemView.noAction.setOnClickListener {
            addNewActionChatMessage("Ok")
        }
    }

    private fun addNewActionChatMessage(actionText: String) {
        onSendNewMessage.invoke(actionText, ChatMessageIntent.RECEIVED_TEXT)
    }

}