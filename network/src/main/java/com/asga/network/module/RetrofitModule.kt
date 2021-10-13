package com.asga.network.module

import com.asga.network.helper.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/* Created by ibrahim.ali on 9/28/2021 */
object RetrofitModule {
    private var retrofit: Retrofit? = null
    private const val GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    private fun provideGson(): Gson? {
        return GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .create()
    }

    fun provideRetrofit(lang: Constants.Lang, token: String, baseUrl: String): Retrofit? {
        val gson = provideGson()
        val okHttpClient = OkHttpClientModule.okHttpClient(lang, token)

        return if (retrofit == null) {
            try {
                getRetrofit(gson!!, okHttpClient, baseUrl)
            } catch (e: Exception) {
                getRetrofit(gson!!, okHttpClient, baseUrl)
            }
        } else {
            retrofit
        }

    }

    private fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient, baseURL: String): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callFactory(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    /*
    fun provideIService(lang: Constants.Lang, token: String): ApiInterface {
        var retrofit = provideRetrofit(lang, token)
        return apiInterface?:retrofit!!.create(ApiInterface::class.java)
    }*/

}