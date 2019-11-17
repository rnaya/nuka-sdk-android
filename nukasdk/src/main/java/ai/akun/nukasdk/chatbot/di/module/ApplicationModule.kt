package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.SdkApplication
import ai.akun.nukasdk.chatbot.di.scope.PerApplication
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SdkApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return application
    }
}