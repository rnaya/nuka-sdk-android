package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import kotlinx.android.synthetic.main.item_row_match_update.view.*

class LiveMatchUpdateViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        when {
            chatMessage.liveMatchUpdate?.bookings != null -> {
                itemView.content.text = "Received booking"
            }
            chatMessage.liveMatchUpdate?.scorers != null -> {
                itemView.content.text = "Received score"
            }
            chatMessage.liveMatchUpdate?.substitutions != null -> {
                itemView.content.text = "Received substitution"
            }
        }
    }

}