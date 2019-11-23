package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.media.MediaPlayer
import android.view.View
import kotlinx.android.synthetic.main.item_row_audio_chat_message_sent.view.*
import kotlinx.android.synthetic.main.item_row_text_chat_message_sent.view.*

class AudioChatMessageSentHolder(itemView: View) : ChatMessageHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        itemView.play.setOnClickListener {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(chatMessage.audioFilePath)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

}