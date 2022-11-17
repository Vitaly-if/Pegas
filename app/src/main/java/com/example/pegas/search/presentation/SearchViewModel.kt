package com.example.pegas.search.presentation

import androidx.lifecycle.ViewModel
import com.example.pegas.main.presentation.NavigationCommunication
import com.example.pegas.main.presentation.NavigationStrategy
import com.example.pegas.main.presentation.Screen

interface SearchViewModel {
    fun showResponse()
    class Base(
        private val navigationCommunication: NavigationCommunication.Mutate
    ) : ViewModel(), SearchViewModel {
        override fun showResponse() {
            navigationCommunication.map(NavigationStrategy.Add(Screen.Responce))
        }
    }
}