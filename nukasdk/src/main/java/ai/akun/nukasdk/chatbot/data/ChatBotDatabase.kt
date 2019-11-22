package ai.akun.nukasdk.chatbot.data

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageDao
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChatMessageEntity::class], version = 1)
abstract class ChatBotDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao

    companion object {
        fun getInstance(context: Context): ChatBotDatabase =
            Room.databaseBuilder(context,
                ChatBotDatabase::class.java,
                "chatBotDb").build()
    }
}