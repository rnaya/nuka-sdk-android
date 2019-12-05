package ai.akun.nukasdk.chatbot.presentation.shared

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

class UnsafeHttpsClient {

    companion object {
        fun getUnsafeOkHttpClientBuilder(): OkHttpClient.Builder {
            val okHttpClientBuilder = OkHttpClient.Builder()
            trustAllSslCertificates(okHttpClientBuilder)
            return okHttpClientBuilder
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
    }
}