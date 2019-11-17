package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.chatbot.presentation.shared.BaseContract

class ChatBotContract {

    interface View {
        fun navigateBack()
        fun disableTextMessageSending()
        fun enableTextMessageSending()
        fun loadMessages(messages: List<String>)
        fun addNewMessage(message: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onBackPressed()
        fun onTextMessageDraftUpdated(content: String)
        fun onTextMessageSent(content: String)
    }
}