package com.example.pegas.main.sl

import androidx.lifecycle.ViewModel
import com.example.pegas.main.presentation.MainViewModel
import com.example.pegas.response.domain.ResponseRepository
import com.example.pegas.response.presentation.ResponseViewModel
import com.example.pegas.response.sl.ProvideResponseRepository
import com.example.pegas.response.sl.ResponseModule
import com.example.pegas.search.presentation.SearchViewModel
import com.example.pegas.search.sl.SearchModule
import java.lang.IllegalStateException

interface DependencyContainer {

    fun <T : ViewModel> module(clasz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clasz: Class<T>): Module<T> {
            throw IllegalStateException("no module found for $clasz")
        }

    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error(),
    ) : DependencyContainer, ProvideResponseRepository {

        private val repository: ResponseRepository by lazy {
            ProvideResponseRepository.Base(core).provideResponseRepository()
        }
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            ResponseViewModel.Base::class.java -> ResponseModule(core, this)
            SearchViewModel.Base::class.java -> SearchModule(core)
            else -> dependencyContainer.module(clasz)
        }

        override fun provideResponseRepository(): ResponseRepository = repository


    }
}