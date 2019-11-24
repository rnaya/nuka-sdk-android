package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.item_row_audio_chat_message_sent.view.*
import java.util.concurrent.TimeUnit


class AudioChatMessageSentHolder(itemView: View) : ChatMessageHolder(itemView) {

    private lateinit var mediaPlayer: MediaPlayer

    private val audioSeekBarHandler = Handler()
    private val updateAudioSeekBarRunnable: Runnable = object : Runnable {
        override fun run() {
            itemView.audioProgressSeekBar.progress = mediaPlayer.currentPosition
            audioSeekBarHandler.postDelayed(this, 15)
        }
    }

    override fun bind(chatMessage: ChatMessage) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(chatMessage.audioFilePath)
        mediaPlayer.prepare()

        setAudioDuration()

        itemView.play.setOnClickListener {
            playAudio()
        }

        itemView.pause.setOnClickListener {
            pauseAudio()
        }

        mediaPlayer.setOnCompletionListener {
            resetAudio()
        }

        itemView.audioProgressSeekBar.setOnTouchListener { _, _ -> true }
    }

    private fun playAudio() {
        mediaPlayer.start()
        itemView.play.visibility = View.GONE
        itemView.pause.visibility = View.VISIBLE
        audioSeekBarHandler.postDelayed(updateAudioSeekBarRunnable, 15)
    }

    private fun pauseAudio() {
        mediaPlayer.pause()
        itemView.pause.visibility = View.GONE
        itemView.play.visibility = View.VISIBLE
        audioSeekBarHandler.removeCallbacks(updateAudioSeekBarRunnable)
    }

    private fun resetAudio() {
        itemView.play.visibility = View.VISIBLE
        itemView.pause.visibility = View.GONE
        audioSeekBarHandler.removeCallbacks(updateAudioSeekBarRunnable)
        itemView.audioProgressSeekBar.progress = 0
    }

    private fun setAudioDuration() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.duration.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(
            mediaPlayer.duration.toLong() - TimeUnit.MINUTES.toMillis(minutes)
        )
        itemView.audioDuration.text = String.format("%02d:%02d",minutes, seconds)
        itemView.audioProgressSeekBar.progress = 0
        itemView.audioProgressSeekBar.max = mediaPlayer.duration
    }
}