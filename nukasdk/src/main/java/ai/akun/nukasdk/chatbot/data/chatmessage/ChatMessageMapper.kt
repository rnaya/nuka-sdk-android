package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage

class ChatMessageMapper {

    fun toDb(from: ChatMessage) = ChatMessageEntity(
        text = from.text,
        sent = from.sent
    )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.sent
    )
}