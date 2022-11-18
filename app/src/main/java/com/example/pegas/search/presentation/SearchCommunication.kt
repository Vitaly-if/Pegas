package com.example.pegas.search.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pegas.main.presentation.Communication

interface SearchCommunication : ObserveSearch {

    fun showProgress(show: Int)

    fun showState(uiState: UiState)

    class Base(
        private val progress: ProgressCommunication,
        private val state: SearchStateCommunication,
    ) : SearchCommunication {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(uiState: UiState) = state.map(uiState)

        override fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }

        override fun observerState(owner: LifecycleOwner, observer: Observer<UiState>) {
            state.observe(owner, observer)
        }

    }
}

interface ObserveSearch {

    fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>)

    fun observerState(owner: LifecycleOwner, observer: Observer<UiState>)

}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Post<Int>(), ProgressCommunication
}

interface SearchStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Post<UiState>(), SearchStateCommunication
}