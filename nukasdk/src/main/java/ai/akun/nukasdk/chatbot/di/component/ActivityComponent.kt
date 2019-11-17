package ai.akun.nukasdk.chatbot.di.component

import ai.akun.nukasdk.chatbot.di.module.ActivityModule
import ai.akun.nukasdk.chatbot.presentation.main.ChatBotActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: ChatBotActivity)
}