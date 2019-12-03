package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.item_row_sent_audio_chat_message.view.*
import java.util.concurrent.TimeUnit

class AudioViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

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
        setUpAudioSeekBar()

        itemView.play.setOnClickListener {
            playAudio()
        }

        itemView.pause.setOnClickListener {
            pauseAudio()
        }

        mediaPlayer.setOnCompletionListener {
            resetAudio()
        }

    }

    private fun setUpAudioSeekBar() {
        itemView.audioProgressSeekBar.setOnTouchListener { _, _ -> true }
        itemView.audioProgressSeekBar.progress = 0
        itemView.audioProgressSeekBar.max = mediaPlayer.duration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            itemView.audioProgressSeekBar.thumb.setTint(itemView.context.getColor(R.color.accentBlue))
            itemView.audioProgressSeekBar.progressDrawable.setTint(itemView.context.getColor(R.color.accentBlue))
        } else {
            @Suppress("DEPRECATION")
            itemView.audioProgressSeekBar.thumb.setColorFilter(R.color.accentBlue, PorterDuff.Mode.SRC_ATOP)
            @Suppress("DEPRECATION")
            itemView.audioProgressSeekBar.progressDrawable.setColorFilter(R.color.accentBlue, PorterDuff.Mode.SRC_ATOP)
        }
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
    }
}