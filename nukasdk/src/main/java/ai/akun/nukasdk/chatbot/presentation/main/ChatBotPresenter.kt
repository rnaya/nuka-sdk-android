package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.chatbot.domain.chatmessage.SendTextChatMessageUseCase
import javax.inject.Inject

class ChatBotPresenter @Inject constructor(private val sendTextChatMessageUseCase: SendTextChatMessageUseCase) : ChatBotContract.Presenter {

    private lateinit var view: ChatBotContract.View

    override fun attach(view: ChatBotContract.View) {
        this.view = view
    }

    override fun onBackPressed() {
        view.navigateBack()
    }

    override fun onTextMessageDraftUpdated(content: String) {
        if(content.isEmpty())
            view.disableTextMessageSending()
        else
            view.enableTextMessageSending()
    }

    override fun onTextMessageSent(content: String) {
        if(content.isNotEmpty())
            view.addNewMessage(sendTextChatMessageUseCase.send(content))
    }

}