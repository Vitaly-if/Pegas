package com.example.pegas.response.data.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

interface CloudModule {

    fun <T> service(clasz: Class<T>): T

    class Base() : CloudModule {

        override fun <T> service(clasz: Class<T>): T {

            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(clasz)
        }
    }


    private companion object {
        const val BASE_URL = "http://foodapi.dzolotov.tech/"
    }
    class Mock : CloudModule{
        override fun <T> service(clasz: Class<T>): T {
            TODO("Not yet implemented")
        }
    }


}


