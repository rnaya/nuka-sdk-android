package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatBotViewModel
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_received_gif_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_sent_text_chat_message.view.content

class ReceivedGifChatMessageHolder(itemView: View) : ChatMessageHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        //TODO loading and error handling. Play/pause??
        Glide.with(itemView.context)
            .load(chatMessage.imageUri)
            .into(itemView.gif)
    }

    override fun setViewModel(chatBotViewModel: ChatBotViewModel) { }
}