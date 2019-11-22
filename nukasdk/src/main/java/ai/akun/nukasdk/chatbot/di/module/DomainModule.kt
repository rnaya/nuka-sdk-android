package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import ai.akun.nukasdk.chatbot.domain.chatmessage.FetchChatMessagesUseCase
import ai.akun.nukasdk.chatbot.domain.chatmessage.SendTextChatMessageUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFetchChatMessagesUseCase(chatMessageRepository: ChatMessageRepository): FetchChatMessagesUseCase {
        return FetchChatMessagesUseCase(chatMessageRepository)
    }

    @Provides
    fun provideSendTextChatMessageUseCase(chatMessageRepository: ChatMessageRepository): SendTextChatMessageUseCase {
        return SendTextChatMessageUseCase(chatMessageRepository)
    }

}