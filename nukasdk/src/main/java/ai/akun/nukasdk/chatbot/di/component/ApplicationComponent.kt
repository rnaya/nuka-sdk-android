package ai.akun.nukasdk.chatbot.di.component

import ai.akun.nukasdk.SdkApplication
import ai.akun.nukasdk.chatbot.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: SdkApplication)
}