package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.chatmessage.events.AddLocallyReceivedMessageEvent
import ai.akun.nukasdk.chatbot.presentation.chatmessage.events.GetLiveMatchUpdatesEvent
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import kotlinx.android.synthetic.main.item_row_received_live_match_available_text_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content
import org.greenrobot.eventbus.EventBus

class LiveMatchAvailableViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        itemView.content.text = chatMessage.text

        itemView.yesAction.setOnClickListener {
            addResponseChatMessage()
            EventBus.getDefault().post(GetLiveMatchUpdatesEvent("g927059"))
        }

        itemView.noAction.setOnClickListener {
            addResponseChatMessage()
        }
    }

    private fun addResponseChatMessage() {
       EventBus.getDefault().post(AddLocallyReceivedMessageEvent("Ok", ChatMessageIntent.RECEIVED_TEXT))
    }

}