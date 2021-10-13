package com.asga.network.module

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

/* Created by ibrahim.ali on 9/28/2021 */
class ApiIUrlInterceptor : Interceptor {

    private var mScheme: String? = null
    private var mHost: String? = null
    private var mPort: Int? = null

    companion object {
        private var sInterceptor: ApiIUrlInterceptor? = null
        fun get(): ApiIUrlInterceptor? {
            if (sInterceptor == null) {
                sInterceptor = ApiIUrlInterceptor()
            }
            return sInterceptor
        }
    }

    fun setInterceptor(url: String?) {
        val httpUrl = url!!.toHttpUrlOrNull()
        mScheme = httpUrl!!.scheme
        mHost = httpUrl.host
        mPort = httpUrl.port
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        if (mScheme != null && mHost != null && mPort != null) {
            val newUrl = original.url.newBuilder()
                .scheme(mScheme!!)
                .host(mHost!!)
                .port(mPort!!)
                .build()
            original = original.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(original)
    }
}