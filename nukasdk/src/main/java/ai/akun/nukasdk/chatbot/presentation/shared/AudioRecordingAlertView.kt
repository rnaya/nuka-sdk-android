package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.R
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.audio_recording_alert_view.view.*


class AudioRecordingAlertView : FrameLayout {

    private val normalStateColor = android.R.color.black
    private val alertStateColor = android.R.color.holo_red_dark

    private var animationStarted = false
    private val animationHandler = Handler()

    private val animationRunnable = Runnable {

        if(micImage.supportImageTintList == ContextCompat.getColorStateList(context, alertStateColor))
            updateMicImageTintColor(normalStateColor)
        else
            updateMicImageTintColor(alertStateColor)

        if (animationStarted) {
            startAnimation()
        }
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.audio_recording_alert_view, this)
        if (visibility == View.VISIBLE) {
            startAnimation()
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        if (visibility == View.VISIBLE) {
            startAnimation()
            return
        }

        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            stopAnimation()
            return
        }

        stopAnimation()
    }

    private fun startAnimation() {
        animationStarted = true
        animationHandler.postDelayed(animationRunnable, 500)
    }

    private fun updateMicImageTintColor(color: Int) {
        micImage.supportImageTintList = ContextCompat.getColorStateList(context, color)
    }

    private fun stopAnimation() {
        animationStarted = false
        animationHandler.removeCallbacks(animationRunnable)
    }

}