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
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession

/* Created by ibrahim.ali on 9/28/2021 */
object OkHttpClientModule {
    const val LOCALE_KEY = "locale"
    const val TOKEN_KEY = "auth-token"

    fun okHttpClient(lang: Constants.Lang, token: String): OkHttpClient {

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
            .hostnameVerifier { _: String?, _: SSLSession? -> true }
            .build()
    }


    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    log(message)
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
        Log.i("asgaHttp", message)
    }

    private fun provideInterceptor(lang: Constants.Lang, token: String): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val builder: Request.Builder = request.newBuilder()
                builder.addHeader(LOCALE_KEY, lang.value!!)
                builder.addHeader(TOKEN_KEY, token.trim { it <= ' ' })
                return chain.proceed(builder.build())
            }
        }
    }

}