package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent

class ChatMessageMapper {

    fun toDb(from: ChatMessage) = ChatMessageEntity(
        text = from.text,
        audioFilePath = from.audioFilePath,
        intent = from.intent.displayName,
        imageUri = from.imageUri
    )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        ChatMessageIntent.values().firstOrNull { it.displayName == from.intent } ?: ChatMessageIntent.RECEIVED_TEXT,
        from.imageUri
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        intent = ChatMessageIntent.values().firstOrNull { it.displayName == from.intent.displayName } ?: ChatMessageIntent.RECEIVED_TEXT,
        imageUri = from.fulfillmentMessages?.get(0)?.card?.imageUri
    )
}