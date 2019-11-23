package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.media.MediaPlayer
import android.view.View
import kotlinx.android.synthetic.main.item_row_audio_chat_message_sent.view.*
import java.util.concurrent.TimeUnit

class AudioChatMessageSentHolder(itemView: View) : ChatMessageHolder(itemView) {

    private lateinit var mediaPlayer: MediaPlayer

    override fun bind(chatMessage: ChatMessage) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(chatMessage.audioFilePath)
        mediaPlayer.prepare()

        setAudioDuration()

        itemView.play.setOnClickListener {
            mediaPlayer.start()
        }
    }

    private fun setAudioDuration() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.duration.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(
            mediaPlayer.duration.toLong() - TimeUnit.MINUTES.toMillis(minutes)
        )
        itemView.audioDuration.text = String.format("%02d:%02d",minutes, seconds)
    }
}