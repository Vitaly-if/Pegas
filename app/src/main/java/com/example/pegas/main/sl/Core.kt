package com.example.pegas.main.sl

import android.content.Context
import com.example.pegas.main.presentation.DispatcherList
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.main.presentation.ResourceProvider
import com.example.pegas.response.data.IdForwardDoc
import com.example.pegas.response.data.cache.CacheModule
import com.example.pegas.response.data.cloud.CloudModule
import com.google.gson.Gson

interface Core : CloudModule, CacheModule, ProvideNavigation, ProvideIdForwardDoc, ProvideResource,
    ProvideMockGson {

    fun provideDispatchers(): DispatcherList


    class Base(
        context: Context,
        private val provideInstances: ProvideInstances,
    ) : Core {
        private val idForwardDoc = IdForwardDoc.Base()
        private val navigationCommunication = NavigationCommunication.Base()
        private val resourceProvider = ResourceProvider.Base(context)
        private val gson = Gson()
        private val dispatcherList by lazy {
            DispatcherList.Base()
        }
        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }
        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun provideDispatchers() = dispatcherList

        override fun <T> service(clasz: Class<T>) = cloudModule.service(clasz)

        override fun provideNavigation() = navigationCommunication
        override fun provideIdForwardDoc() = idForwardDoc
        override fun provideResource() = resourceProvider
        override fun provideMockGson() = gson

    }
}

interface ProvideResource {
    fun provideResource(): ResourceProvider
}

interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}

interface ProvideMockGson {
    fun provideMockGson(): Gson
}

interface ProvideIdForwardDoc {
    fun provideIdForwardDoc(): IdForwardDoc.Mutable
}