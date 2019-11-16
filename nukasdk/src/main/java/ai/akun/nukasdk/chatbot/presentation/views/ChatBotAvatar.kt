package ai.akun.nukasdk.chatbot.presentation.views

import ai.akun.nukasdk.R
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


class ChatBotAvatar : FrameLayout {

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
        inflate(context, R.layout.avatar_view, this)
    }

}