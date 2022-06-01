package com.asga.network.module

import android.util.Log
import com.asga.network.BuildConfig
import com.asga.network.helper.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException

object UnSafeOkHttpClientModule {
    const val LOCALE_KEY = "locale"
    const val TOKEN_KEY = "auth-token"
    private const val NETWORK_LOG_TAG = "asga-network"
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

        }
    )

    fun UnSafeOkHttpClient(lang: Constants.Lang, token: String): OkHttpClient {
        val sslSocketFactory: SSLSocketFactory = provideSslSocketFactory()
        val headerInterceptor = provideInterceptor(lang, token)
        val httpLoggingInterceptor = httpLoggingInterceptor()
        val apiIUrlInterceptor = provideBaseURLInterceptor()
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(apiIUrlInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _: String?, _: SSLSession? -> true }
            .build()
    }

    private fun provideSslSocketFactory(): SSLSocketFactory {
        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.socketFactory
    }


    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    logMessage(message)
                }
            })
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor

    }


    private fun provideBaseURLInterceptor(): ApiIUrlInterceptor {
        return ApiIUrlInterceptor.get()!!
    }


    private fun logMessage(message: String) {
        Log.i(NETWORK_LOG_TAG, message)
    }

    private fun provideInterceptor(lang: Constants.Lang, token: String): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val builder: Request.Builder = request.newBuilder()
                builder.addHeader(LOCALE_KEY, lang.value!!)
                if (token.isNotEmpty())
                    builder.addHeader(TOKEN_KEY, token.trim { it <= ' ' })
                return chain.proceed(builder.build())
            }
        }
    }
}