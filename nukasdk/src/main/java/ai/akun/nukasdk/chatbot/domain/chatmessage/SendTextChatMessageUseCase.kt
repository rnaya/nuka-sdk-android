package ai.akun.nukasdk.chatbot.domain.chatmessage

class SendTextChatMessageUseCase {

    fun send(text: String): ChatMessage {
        return ChatMessage(text, ChatMessageType.RECEIVED)
    }
}