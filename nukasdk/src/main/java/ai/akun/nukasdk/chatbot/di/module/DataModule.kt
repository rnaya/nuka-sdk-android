package ai.akun.nukasdk.chatbot.di.module

import ai.akun.nukasdk.BuildConfig
import ai.akun.nukasdk.chatbot.data.ChatBotDatabase
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageDao
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageMapper
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageService
import ai.akun.nukasdk.chatbot.data.webhook.WebhookService
import android.annotation.SuppressLint
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.X509TrustManager
import javax.net.ssl.SSLContext
import android.util.Base64.NO_WRAP
import android.util.Base64
import java.security.SecureRandom


@Module
class DataModule {

    @Provides
    fun provideChatMessageDao(context: Context): ChatMessageDao {
        return ChatBotDatabase.getInstance(context).chatMessageDao()
    }

    @Provides
    fun provideChatMessageRepository(chatMessageDao: ChatMessageDao,
                                     chatMessageMapper: ChatMessageMapper,
                                     chatMessageService: ChatMessageService): ChatMessageRepository {
        return ChatMessageRepository(chatMessageDao, chatMessageMapper, chatMessageService)
    }

    @Provides
    fun provideChatMessageMapper(): ChatMessageMapper {
        return ChatMessageMapper()
    }

    @Provides
    fun provideChatMessageService(): ChatMessageService {
        val retrofit = getRetrofitInstance(
            getAuthenticationInterceptor(
                BuildConfig.CHAT_MESSAGE_API_AUTH_USERNAME,
                BuildConfig.CHAT_MESSAGE_API_PASSWORD
            )
        )
        return retrofit.create(ChatMessageService::class.java)
    }

    @Provides
    fun provideWebhookService(): WebhookService {
        val retrofit = getRetrofitInstance(
            getAuthenticationInterceptor(
                BuildConfig.WEBHOOK_API_AUTH_USERNAME,
                BuildConfig.WEBHOOK_API_PASSWORD
            )
        )
        return retrofit.create(WebhookService::class.java)
    }

    private fun getRetrofitInstance(authenticationInterceptor: Interceptor): Retrofit {
        val okHttpClient = getOkHttpClient(authenticationInterceptor)
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    private fun getOkHttpClient(authenticationInterceptor: Interceptor) : OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        okHttpClientBuilder.addInterceptor(authenticationInterceptor)

        if (BuildConfig.DEBUG)  {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            trustAllSslCertificates(okHttpClientBuilder)
        }

        return okHttpClientBuilder
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun trustAllSslCertificates(okHttpClientBuilder: OkHttpClient.Builder) {
        val trustAllCerts = arrayOf(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory
        okHttpClientBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0])
        okHttpClientBuilder.hostnameVerifier(HostnameVerifier { _, _ -> true })
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