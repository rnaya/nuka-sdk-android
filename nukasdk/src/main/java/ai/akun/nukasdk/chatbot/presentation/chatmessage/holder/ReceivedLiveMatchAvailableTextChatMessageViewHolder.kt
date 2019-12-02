package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.item_row_received_live_match_available_text_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content

class ReceivedLiveMatchAvailableTextChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    private lateinit var onSendNewMessage: ((String, ChatMessageIntent) -> Unit)

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String, ChatMessageIntent) -> Unit)?) {
        this.onSendNewMessage = onSendNewMessage!!
        itemView.content.text = chatMessage.text

        itemView.yesAction.setOnClickListener {
            addNewActionChatMessage("Ok", ChatMessageIntent.RECEIVED_TEXT)

            Handler().postDelayed({
                addNewActionChatMessage("Sebastian Mendez", ChatMessageIntent.RECEIVED_TEXT)
            }, 2000)

            Handler().postDelayed({
                addNewActionChatMessage("Jaime Valdes", ChatMessageIntent.RECEIVED_TEXT)
            }, 4000)

            Handler().postDelayed({
                addNewActionChatMessage("Entra: Sergio Bareiro \n Sale: Javier Urzua", ChatMessageIntent.RECEIVED_TEXT)
            }, 6000)
        }

        itemView.noAction.setOnClickListener {
            addNewActionChatMessage("Ok", ChatMessageIntent.RECEIVED_TEXT)
        }
    }

    private fun addNewActionChatMessage(actionText: String, chatMessageIntent: ChatMessageIntent) {
        onSendNewMessage.invoke(actionText, chatMessageIntent)
    }

}