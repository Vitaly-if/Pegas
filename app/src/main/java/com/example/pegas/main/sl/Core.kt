package com.example.pegas.main.sl

import android.content.Context
import com.example.pegas.main.presentation.DispatcherList
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.response.data.cache.CacheModule
import com.example.pegas.response.data.cloud.CloudModule

interface Core : CloudModule, CacheModule, ProvideNavigation{

    fun provideDispatchers(): DispatcherList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {
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
        }
    }


interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}