package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.chatbot.domain.chatmessage.SendTextChatMessageUseCase
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotContract
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotPresenter
import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(textChatMessageUseCase: SendTextChatMessageUseCase): ChatBotContract.Presenter {
        return ChatBotPresenter(textChatMessageUseCase)
    }

}