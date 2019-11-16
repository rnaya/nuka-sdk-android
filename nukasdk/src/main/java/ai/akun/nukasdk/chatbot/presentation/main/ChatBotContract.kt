package ai.akun.nukasdk.chatbot.presentation.main

class ChatBotContract {

    interface View {
        fun navigateBack()
        fun loadMessages(messages: List<String>)
        fun addNewMessage(message: String)
    }

    interface Presenter {
        fun onBackPressed()
        fun onTextMessageSent(content: String)
    }
}