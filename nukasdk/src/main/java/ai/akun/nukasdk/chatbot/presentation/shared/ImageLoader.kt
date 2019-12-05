package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.R
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class ImageLoader {

    companion object {

        fun load(imageUrl: String, imageView: ImageView, errorPlaceholder: Int? = R.drawable.image_placeholder) {
            val circularProgressDrawable = CircularProgressDrawable(imageView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 15f
            circularProgressDrawable.setColorSchemeColors(R.color.accentBlue)
            circularProgressDrawable.start()

            GlideApp.with(imageView.context)
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .error(errorPlaceholder!!)
                .into(imageView)
        }

        fun load(resourceId: Int, imageView: ImageView) {
            Glide.with(imageView.context)
                .load(resourceId)
                .into(imageView)
        }

    }

}