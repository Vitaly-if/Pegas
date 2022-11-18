package com.example.pegas.main.sl

import android.content.Context
import com.example.pegas.main.presentation.DispatcherList
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.response.data.IdForwardDoc
import com.example.pegas.response.data.cache.CacheModule
import com.example.pegas.response.data.cloud.CloudModule

interface Core : CloudModule, CacheModule, ProvideNavigation, ProvideIdForwardDoc{

    fun provideDispatchers(): DispatcherList


    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {
        private val idForwardDoc = IdForwardDoc.Base()
        private val navigationCommunication = NavigationCommunication.Base()
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
        override fun provideNavigation() = navigationCommunication
        override fun provideIdForwardDoc() = idForwardDoc
    }
    }


interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}
interface ProvideIdForwardDoc {
    fun provideIdForwardDoc(): IdForwardDoc.Mutable
}