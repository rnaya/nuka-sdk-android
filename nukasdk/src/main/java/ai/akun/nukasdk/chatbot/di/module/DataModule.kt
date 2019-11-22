package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.chatbot.data.ChatBotDatabase
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageDao
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageMapper
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideChatMessageDao(context: Context): ChatMessageDao {
        return ChatBotDatabase.getInstance(context).chatMessageDao()
    }

    @Provides
    fun provideChatMessageRepository(chatMessageDao: ChatMessageDao, chatMessageMapper: ChatMessageMapper): ChatMessageRepository {
        return ChatMessageRepository(chatMessageDao, chatMessageMapper)
    }

    @Provides
    fun provideChatMessageMapper(): ChatMessageMapper {
        return ChatMessageMapper()
    }
}