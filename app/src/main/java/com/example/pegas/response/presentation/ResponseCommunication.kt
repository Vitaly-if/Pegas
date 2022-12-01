package com.example.pegas.response.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pegas.main.presentation.Communication

/**
 * @author Vitaly.N on 30.11.2022.
 */
interface ResponseCommunication : ObserveResponse {

    fun showProgress(show: Int)
    fun showState(forwardDocUiState: ForwardDocUiState)
    fun show(forwardDocUi: ForwardDocUi)
    class Base(
        private val progress: ProgressCommunication,
        private val state: ResponseStateCommunication,
        private val forwardDoc: ForwardDocUiCommunication,
    ) : ResponseCommunication {
        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(forwardDocUiState: ForwardDocUiState) = state.map(forwardDocUiState)

        override fun show(forwardDocUi: ForwardDocUi) = forwardDoc.map(forwardDocUi)

        override fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observerState(owner: LifecycleOwner, observer: Observer<ForwardDocUiState>) =
            state.observe(owner, observer)

        override fun observeForwardDocUi(owner: LifecycleOwner, observer: Observer<ForwardDocUi>) =
            forwardDoc.observe(owner, observer)
    }
}

interface ObserveResponse {
    fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observerState(owner: LifecycleOwner, observer: Observer<ForwardDocUiState>)
    fun observeForwardDocUi(owner: LifecycleOwner, observer: Observer<ForwardDocUi>)
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Post<Int>(), ProgressCommunication
}

interface ResponseStateCommunication : Communication.Mutable<ForwardDocUiState> {
    class Base : Communication.Post<ForwardDocUiState>(), ResponseStateCommunication
}

interface ForwardDocUiCommunication : Communication.Mutable<ForwardDocUi> {
    class Base : Communication.Post<ForwardDocUi>(), ForwardDocUiCommunication
}
