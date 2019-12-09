package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.BuildConfig
import ai.akun.nukasdk.chatbot.data.ChatBotDatabase
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageDao
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageMapper
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageService
import ai.akun.nukasdk.chatbot.data.webhook.WebhookService
import ai.akun.nukasdk.chatbot.presentation.shared.RetrofitBuilder
import android.content.Context
import android.util.Base64
import android.util.Base64.NO_WRAP
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideChatMessageDao(context: Context): ChatMessageDao {
        return ChatBotDatabase.getInstance(context).chatMessageDao()
    }

    @Provides
    fun provideChatMessageRepository(chatMessageDao: ChatMessageDao,
                                     chatMessageMapper: ChatMessageMapper,
                                     chatMessageService: ChatMessageService,
                                     webhookService: WebhookService): ChatMessageRepository {
        return ChatMessageRepository(chatMessageDao, chatMessageMapper, chatMessageService, webhookService)
    }

    @Provides
    fun provideChatMessageMapper(): ChatMessageMapper {
        return ChatMessageMapper()
    }

    @Provides
    fun provideChatMessageService(context: Context): ChatMessageService {
        val retrofit = getRetrofitInstance(
            getAuthenticationInterceptor(
                BuildConfig.CHAT_MESSAGE_API_AUTH_USERNAME,
                BuildConfig.CHAT_MESSAGE_API_PASSWORD
            ),
            context
        )
        return retrofit.create(ChatMessageService::class.java)
    }

    @Provides
    fun provideWebhookService(context: Context): WebhookService {
        val retrofit = getRetrofitInstance(
            getAuthenticationInterceptor(
                BuildConfig.WEBHOOK_API_AUTH_USERNAME,
                BuildConfig.WEBHOOK_API_PASSWORD
            ),
            context
        )
        return retrofit.create(WebhookService::class.java)
    }

    private fun getRetrofitInstance(authenticationInterceptor: Interceptor, context: Context): Retrofit {
        val okHttpClient = getOkHttpClient(authenticationInterceptor, context)
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    private fun getOkHttpClient(authenticationInterceptor: Interceptor, context: Context) : OkHttpClient {
        val okHttpClientBuilder = RetrofitBuilder.getInstance(context)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        okHttpClientBuilder.addInterceptor(authenticationInterceptor)

        return okHttpClientBuilder
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun getAuthenticationInterceptor(userName: String, password: String) : Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val credentials = "$userName:$password"
                val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), NO_WRAP)

                val originalRequest = chain.request()
                val securedRequest = originalRequest.newBuilder()
                    .header("Authorization", basic)
                    .build()

                return chain.proceed(securedRequest)
            }
        }
    }

}