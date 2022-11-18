package com.example.pegas.search.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.main.presentation.NavigationStrategy
import com.example.pegas.main.presentation.Screen
import com.example.pegas.search.domain.SearchIteractor

interface SearchViewModel : ClearError, ObserveSearch, UpdateState {
    fun showResponse(toString: String)
    class Base(
        private val communication: SearchCommunication,
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val iteractor: SearchIteractor,
    ) : ViewModel(), SearchViewModel {
        override fun showResponse(inputId: String) {
            iteractor.saveIdForwarDoc(inputId)
            navigationCommunication.map(NavigationStrategy.Add(Screen.Responce))
            communication.showState(UiState.Gone())
        }

        override fun clearError() {
            communication.showState(UiState.CleanError())
        }

        override fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>) {

        }

        override fun observerState(owner: LifecycleOwner, observer: Observer<UiState>) =
            communication.observerState(owner, observer)

        override fun updateState() {
            communication.showState(UiState.Success())
        }
    }
}

interface ClearError {
    fun clearError()
}
interface UpdateState {
    fun updateState()
}