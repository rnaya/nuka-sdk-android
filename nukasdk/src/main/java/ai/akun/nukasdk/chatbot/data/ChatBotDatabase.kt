package ai.akun.nukasdk.chatbot.data

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageDao
import ai.akun.nukasdk.chatbot.data.chatmessage.entities.ChatMessageEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ChatMessageEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ChatBotDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao

    companion object {
        fun getInstance(context: Context): ChatBotDatabase =
            Room.databaseBuilder(context,
                ChatBotDatabase::class.java,
                "chatBotDb").build()
    }
}