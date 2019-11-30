package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.ButtonEntity
import ai.akun.nukasdk.chatbot.data.chatmessage.entities.CardEntity
import ai.akun.nukasdk.chatbot.data.chatmessage.entities.ChatMessageEntity
import ai.akun.nukasdk.chatbot.presentation.main.Button
import ai.akun.nukasdk.chatbot.presentation.main.Card
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent

class ChatMessageMapper {

    fun toDb(from: ChatMessage) =
        ChatMessageEntity(
            text = from.text,
            audioFilePath = from.audioFilePath,
            intent = from.intent.displayName,
            data = from.data?.map {
                CardEntity(
                    it.title,
                    it.subtitle,
                    it.imageUri,
                    it.buttons?.map { button ->
                        ButtonEntity(
                            button.text,
                            button.postback
                        )
                    }
                )
            }
        )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        ChatMessageIntent.values().firstOrNull { it.displayName == from.intent } ?: ChatMessageIntent.RECEIVED_TEXT,
        from.data?.map {
            Card(
                it.title,
                it.subtitle,
                it.imageUri,
                it.buttons?.map {button ->
                    Button(
                        button.text,
                        button.postback
                    )
                }
            )
        }
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        intent = ChatMessageIntent.values().firstOrNull { it.displayName == from.intent.displayName } ?: ChatMessageIntent.RECEIVED_TEXT,
        data = from.fulfillmentMessages?.map {
            Card(
                it.card?.title ?: "",
                it.card?.subtitle ?: "",
                it.card?.imageUri ?: "",
                it.card?.buttons?.map { button ->
                    Button(button.text, button.postback)
                }) }
    )
}