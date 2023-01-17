package com.example.pegas.main.sl

import android.content.Context
import com.example.pegas.response.data.cache.CacheModule
import com.example.pegas.response.data.cloud.CloudModule

interface ProvideInstances {
    fun provideCloudModule(): CloudModule
    fun provideCacheModule(): CacheModule

    class Release(private val context: Context) : ProvideInstances {
        override fun provideCacheModule() = CacheModule.Base(context)
        override fun provideCloudModule() = CloudModule.Base()
    }



}
