package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.R
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class ImageLoader {

    companion object {

        fun load(imageUrl: String, imageView: ImageView) {
            val circularProgressDrawable = CircularProgressDrawable(imageView.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 25f
            circularProgressDrawable.setColorSchemeColors(R.color.accentBlue)
            circularProgressDrawable.start()

            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.image_placeholder)
                .into(imageView)
        }

    }

}