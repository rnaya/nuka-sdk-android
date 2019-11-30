package ai.akun.nukasdk.chatbot.data.chatmessage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_message")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String?,
    val audioFilePath: String?,
    val intent: String,
    val data: List<CardEntity>?
)