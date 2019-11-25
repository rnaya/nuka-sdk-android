package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content
import kotlinx.android.synthetic.main.item_row_received_welcome_text_chat_message.view.*

class ReceivedWelcomeTextChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    private lateinit var chatBotViewModel: ChatBotViewModel

    override fun setViewModel(chatBotViewModel: ChatBotViewModel) {
        this.chatBotViewModel = chatBotViewModel
    }

    override fun bind(chatMessage: ChatMessage) {
        itemView.content.text = chatMessage.text

        itemView.matchesAction.setOnClickListener {
            val action = itemView.context.getString(R.string.matches)
            addNewActionChatMessage(action)
        }

        itemView.positionsAction.setOnClickListener {
            val action = itemView.context.getString(R.string.positions)
            addNewActionChatMessage(action)
        }

        itemView.playersAction.setOnClickListener {
            val action = itemView.context.getString(R.string.players)
            addNewActionChatMessage(action)
        }
    }

    private fun addNewActionChatMessage(actionText: String) {
        chatBotViewModel.sendTextChatMessage(actionText)
    }

}