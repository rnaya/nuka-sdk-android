package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.chatbot.domain.chatmessage.FetchChatMessagesUseCase
import ai.akun.nukasdk.chatbot.domain.chatmessage.SendTextChatMessageUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFetchChatMessagesUseCase(): FetchChatMessagesUseCase {
        return FetchChatMessagesUseCase()
    }

    @Provides
    fun provideSendTextChatMessageUseCase(): SendTextChatMessageUseCase {
        return SendTextChatMessageUseCase()
    }

}