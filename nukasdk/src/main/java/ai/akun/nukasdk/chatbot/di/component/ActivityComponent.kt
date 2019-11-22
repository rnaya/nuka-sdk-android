package ai.akun.nukasdk.chatbot.di.component

import ai.akun.nukasdk.chatbot.di.module.ActivityModule
import ai.akun.nukasdk.chatbot.di.module.DataModule
import ai.akun.nukasdk.chatbot.di.module.ViewModelModule
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class, ViewModelModule::class, DataModule::class])
interface ActivityComponent {
    fun inject(mainActivity: ChatBotActivity)
}