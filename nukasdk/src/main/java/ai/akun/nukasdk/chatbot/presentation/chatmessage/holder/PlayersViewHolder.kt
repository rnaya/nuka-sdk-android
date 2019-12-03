package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.PlayersAdapter
import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.SocialNetworksAdapter
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_row_scrollable_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_received_text_chat_message.view.*

class PlayersViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        if(!chatMessage.text.isNullOrBlank() && chatMessage.text != ChatMessageIntent.RECEIVED_PLAYERS.displayName) {
            itemView.scrollableList.visibility = View.GONE
            itemView.messageContainer.visibility = View.VISIBLE
            itemView.content.text = chatMessage.text
        } else {
            val adapter = PlayersAdapter()
            itemView.scrollableList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.scrollableList.adapter = adapter
            adapter.load(chatMessage.webhookPayload?.players!!)
        }
    }

}