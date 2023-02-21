package com.example.pegas.response.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.main.presentation.NavigationStrategy
import com.example.pegas.response.presentation.ForwardDocUi
import com.example.pegas.response.presentation.ForwardDocUiState
import com.example.pegas.response.presentation.ResponseCommunication
import com.example.pegas.search.presentation.UiState

/**
 * @author Vitaly.N on 21.02.2023.
 */
abstract class BaseTest {

    protected class TestNavigationCommunication : NavigationCommunication.Mutable {

        lateinit var strategy: NavigationStrategy
        var count = 0

        override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) = Unit

        override fun map(sourse: NavigationStrategy) {
            strategy = sourse
            count++
        }
    }

    protected class TestCommunications : ResponseCommunication {

        val progressCalledList = mutableListOf<Int>()
        val stateCalledList = mutableListOf<ForwardDocUiState>()
        var timeShowList = 0
        val forwardDocUiList = mutableListOf<ForwardDocUi>()

        override fun showProgress(show: Int) {
            progressCalledList.add(show)
        }

        override fun showState(forwardDocUiState: ForwardDocUiState) {
            stateCalledList.add(forwardDocUiState)
        }

        override fun show(forwardDocUi: ForwardDocUi) {
            timeShowList++
            forwardDocUiList.add(forwardDocUi)
        }

        override fun observerProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit

        override fun observerState(owner: LifecycleOwner, observer: Observer<ForwardDocUiState>) =
            Unit

        override fun observeForwardDocUi(owner: LifecycleOwner, observer: Observer<ForwardDocUi>) =
            Unit

    }
}