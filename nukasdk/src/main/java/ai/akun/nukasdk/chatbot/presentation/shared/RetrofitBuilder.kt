package ai.akun.nukasdk.chatbot.presentation.shared

import ai.akun.nukasdk.R
import android.content.Context
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.InputStream
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


class RetrofitBuilder {

    companion object {

        private var httpClientBuilder: OkHttpClient.Builder? = null

        fun getInstance(context: Context): OkHttpClient.Builder {
                httpClientBuilder = OkHttpClient.Builder()
                initSSL(context)
                return httpClientBuilder!!
        }

        private fun initSSL(context: Context) {
            var sslContext: SSLContext? = null
            try {
                sslContext =
                    createCertificate(context.resources.openRawResource(R.raw.raisting_co_ssl_certificate))
            } catch (e: Exception) {
                Timber.d(e, "Error while creating SSL context")
            }
            if (sslContext != null) {
                httpClientBuilder!!.sslSocketFactory(
                    sslContext.socketFactory,
                    systemDefaultTrustManager()
                )
            }
        }

        private fun createCertificate(trustedCertificateIS: InputStream): SSLContext? {
            val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
            val ca: Certificate
            ca = trustedCertificateIS.use {
                cf.generateCertificate(it)
            }
            // creating a KeyStore containing our trusted CAs
            val keyStoreType: String = KeyStore.getDefaultType()
            val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)
            keyStore.load(null, null)
            keyStore.setCertificateEntry("ca", ca)
            // creating a TrustManager that trusts the CAs in our KeyStore
            val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
            val tmf: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
            tmf.init(keyStore)
            // creating an SSLSocketFactory that uses our TrustManager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, null)
            return sslContext
        }

        private fun systemDefaultTrustManager(): X509TrustManager {
            return try {
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers: Array<TrustManager> = trustManagerFactory.trustManagers
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                    "Unexpected default trust managers: " + trustManagers.contentToString()
                }
                trustManagers[0] as X509TrustManager
            } catch (e: GeneralSecurityException) {
                throw AssertionError() // The system has no TLS. Just give up.
            }
        }
    }

}