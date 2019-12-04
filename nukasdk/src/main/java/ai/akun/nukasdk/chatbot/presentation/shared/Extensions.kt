package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.chatbot.presentation.main.Venue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Venue.hasValidLocation() = this.latitude != null && this.longitude != null