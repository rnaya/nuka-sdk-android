package ai.akun.nukasdk.chatbot.data.chatmessage.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_message")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String?,
    val audioFilePath: String?,
    val intent: String,
    val cardPayload: List<CardEntity>?,
    @Embedded
    val webhookPayload: WebhookPayloadEntity?,
    @Embedded
    val liveMatchUpdateEntity: LiveMatchUpdateEntity?
)