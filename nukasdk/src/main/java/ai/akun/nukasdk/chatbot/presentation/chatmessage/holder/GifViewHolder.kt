package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import android.view.View
import kotlinx.android.synthetic.main.item_row_received_gif_chat_message.view.*

class GifViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        ImageLoader.load(chatMessage.cardsPayload?.get(0)?.imageUri ?: "", itemView.gif)
    }

}