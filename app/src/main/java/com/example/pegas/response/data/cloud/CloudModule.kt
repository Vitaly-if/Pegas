package com.example.pegas.response.data.cloud

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

interface CloudModule {

    fun <T> service(clasz: Class<T>): T

    class Base() : CloudModule {

        override fun <T> service(clasz: Class<T>): T {

            val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(BasicAuthInterceptor("1art", "021869r"))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(clasz)
        }
    }

    private companion object {
        const val BASE_URL = "http://192.168.0.177/UT82_/hs/"
    }

}

class BasicAuthInterceptor(user: String, password: String) : Interceptor {

    private val credentials: String = Credentials.basic(user, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain!!.request()
        val authenticatedRequest = request
            .newBuilder()
            .header("Authorization", credentials)
            .build()
        return chain.proceed(authenticatedRequest)
    }

}

