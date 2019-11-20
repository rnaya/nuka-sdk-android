package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessageAction
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessageType

class ChatBotPresenter : ChatBotContract.Presenter {

    private lateinit var view: ChatBotContract.View

    override fun attach(view: ChatBotContract.View) {
        this.view = view

        val mockMessages = mutableListOf<ChatMessage>()
        val mockActions = mutableListOf<ChatMessageAction>()
        mockActions.add(ChatMessageAction("Partidos"))
        mockActions.add(ChatMessageAction("Posiciones"))
        mockActions.add(ChatMessageAction("Jugadores"))
        mockMessages.add(ChatMessage("Bienvenido! Me puedes preguntar cosas como: \n" +
                "- Cuando es el proximo partido?\n" +
                "- Como está el resultado?\n" +
                "- Dame las redes sociales de Lionel Messi", ChatMessageType.RECEIVED, mockActions))
        mockMessages.add(ChatMessage("Cuando es el proximo partido?", ChatMessageType.SENT))
        view.loadMessages(mockMessages)
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
            view.addNewMessage(ChatMessage(content, ChatMessageType.SENT))
    }

}