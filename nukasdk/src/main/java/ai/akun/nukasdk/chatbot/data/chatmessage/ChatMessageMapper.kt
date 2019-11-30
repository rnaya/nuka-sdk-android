package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.Button
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent

class ChatMessageMapper {

    fun toDb(from: ChatMessage) = ChatMessageEntity(
        text = from.text,
        audioFilePath = from.audioFilePath,
        intent = from.intent.displayName
    )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        ChatMessageIntent.values().firstOrNull { it.displayName == from.intent } ?: ChatMessageIntent.RECEIVED_TEXT,
        null
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        intent = ChatMessageIntent.values().firstOrNull { it.displayName == from.intent.displayName } ?: ChatMessageIntent.RECEIVED_TEXT,
        data = from.fulfillmentMessages?.map {
            ai.akun.nukasdk.chatbot.presentation.main.Card(
                it.card.title,
                it.card.subtitle,
                it.card.imageUri,
                it.card.buttons?.map { button ->
                    Button(button.text, button.postback)
                }) }
    )
}