package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content
import kotlinx.android.synthetic.main.item_row_received_welcome_text_chat_message.view.*

class ReceivedWelcomeTextChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    private lateinit var onSendNewMessage: ((String) -> Unit)

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String) -> Unit)?) {
        this.onSendNewMessage = onSendNewMessage!!
        itemView.content.text = chatMessage.text

        itemView.matchesAction.setOnClickListener {
            val action = itemView.context.getString(R.string.next_match_question)
            addNewActionChatMessage(action)
        }

        itemView.positionsAction.setOnClickListener {
            val action = itemView.context.getString(R.string.table_question)
            addNewActionChatMessage(action)
        }

        itemView.playersAction.setOnClickListener {
            val action = itemView.context.getString(R.string.players_question)
            addNewActionChatMessage(action)
        }
    }

    private fun addNewActionChatMessage(actionText: String) {
        onSendNewMessage.invoke(actionText)
    }

}