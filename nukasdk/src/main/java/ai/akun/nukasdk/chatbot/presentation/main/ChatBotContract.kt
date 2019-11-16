package ai.akun.nukasdk.chatbot.presentation.main

class ChatBotContract {

    interface View {
        fun navigateBack()
        fun disableTextMessageSending()
        fun enableTextMessageSending()
        fun loadMessages(messages: List<String>)
        fun addNewMessage(message: String)
    }

    interface Presenter {
        fun onBackPressed()
        fun onTextMessageDraftUpdated(content: String)
        fun onTextMessageSent(content: String)
    }
}