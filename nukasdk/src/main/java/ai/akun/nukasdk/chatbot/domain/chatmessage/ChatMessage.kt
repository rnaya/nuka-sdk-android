package ai.akun.nukasdk.chatbot.domain.chatmessage

data class ChatMessage(
    val content: String,
    val type: ChatMessageType,
    val actions: List<ChatMessageAction>? = listOf()
)