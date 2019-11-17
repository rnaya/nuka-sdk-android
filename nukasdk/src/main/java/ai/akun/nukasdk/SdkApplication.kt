package ai.akun.nukasdk

import ai.akun.nukasdk.chatbot.di.component.ApplicationComponent
import ai.akun.nukasdk.chatbot.di.component.DaggerApplicationComponent
import ai.akun.nukasdk.chatbot.di.module.ApplicationModule
import android.app.Application
import timber.log.Timber

class SdkApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: SdkApplication private set
    }
}