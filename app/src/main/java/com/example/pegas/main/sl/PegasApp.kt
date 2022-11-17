package com.example.pegas.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.pegas.BuildConfig
import com.example.pegas.response.domain.ResponseRepository
import com.example.pegas.response.sl.ProvideResponseRepository

class PegasApp : Application(), ProvideViewModel, ProvideResponseRepository {

    private lateinit var viewModelFactory: ViewModelsFactory
    private lateinit var dependencyContainer: DependencyContainer.Base

    override fun onCreate() {
        super.onCreate()
        val provideInstances = if(BuildConfig.DEBUG)
            ProvideInstances.Mock(this)
        else
            ProvideInstances.Release(this)
        dependencyContainer = DependencyContainer.Base(Core.Base(this, provideInstances))
        viewModelFactory = ViewModelsFactory(dependencyContainer)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelFactory)[clazz]

    override fun provideResponseRepository() = dependencyContainer.provideResponseRepository()

}