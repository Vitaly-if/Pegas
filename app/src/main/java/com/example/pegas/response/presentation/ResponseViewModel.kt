package com.example.pegas.response.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.response.domain.ResponseInteractor

interface ResponseViewModel : ObserveResponse {

    class Base(
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val handleResult: HandleForwardDocRequest,
        private val interactor: ResponseInteractor,
        private val communications: ResponseCommunication,
    ) : ViewModel(),
        ResponseViewModel {

        fun fetchForwardDoc() {
            handleResult.handle(viewModelScope) {
                interactor.fetchForwardDoc()
            }
        }

        override fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            communications.observerProgress(owner, observer)
        }

        override fun observerState(owner: LifecycleOwner, observer: Observer<ForwardDocUiState>) {
            communications.observerState(owner, observer)
        }

        override fun observeForwardDocUi(owner: LifecycleOwner, observer: Observer<ForwardDocUi>) =
            communications.observeForwardDocUi(owner, observer)
    }
}