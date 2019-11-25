package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage

class ChatMessageMapper {

    fun toDb(from: ChatMessage) = ChatMessageEntity(
        text = from.text,
        audioFilePath = from.audioFilePath,
        intent = from.intent,
        imageUri = from.imageUri
    )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        from.intent,
        from.imageUri
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        intent = from.intent.displayName,
        imageUri = "https://media.giphy.com/media/8RQJ4RW9PPxRK/giphy.gif"
    )
}