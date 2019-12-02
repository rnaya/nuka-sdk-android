package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import timber.log.Timber
import java.lang.Exception

class UrlNavigator {

    companion object {

        fun navigate(url: String, context: Context) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                ContextCompat.startActivity(context, intent, null)
            } catch (e: Exception) {
                Timber.e(e, "Error while trying to open an URL")
                Toast.makeText(context, context.getString(R.string.url_error_message), Toast.LENGTH_SHORT).show()
            }
        }

    }

}