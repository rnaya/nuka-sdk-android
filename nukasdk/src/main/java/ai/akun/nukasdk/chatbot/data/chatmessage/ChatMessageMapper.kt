package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageType

class ChatMessageMapper {

    fun toDb(from: ChatMessage) = ChatMessageEntity(
        text = from.text,
        audioFilePath = from.audioFilePath,
        intent = from.type.intent,
        imageUri = from.imageUri
    )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        ChatMessageType.values().firstOrNull { it.intent == from.intent } ?: ChatMessageType.RECEIVED_TEXT,
        from.imageUri
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        type = ChatMessageType.values().firstOrNull { it.intent == from.intent.displayName } ?: ChatMessageType.RECEIVED_TEXT,
        imageUri = from.fulfillmentMessages?.get(0)?.card?.imageUri
    )
}