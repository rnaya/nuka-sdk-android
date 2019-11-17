package ai.akun.nukasdk.chatbot.presentation.main

class ChatBotPresenter : ChatBotContract.Presenter {

    private lateinit var view: ChatBotContract.View

    override fun attach(view: ChatBotContract.View) {
        this.view = view

        val mockMessages = mutableListOf<String>()
        mockMessages.add("Hi")
        mockMessages.add("How are you?")
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
            view.addNewMessage(content)
    }

}