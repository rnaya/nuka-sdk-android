package ai.akun.nukasdk.chatbot.presentation.shared

class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
    }

}