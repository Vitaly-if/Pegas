package com.example.pegas.response.presentation

import androidx.lifecycle.ViewModel
import com.example.pegas.main.presentation.NavigationCommunication

interface ResponseViewModel {

    class Base(private val navigationCommunication: NavigationCommunication.Mutate) : ViewModel(),
        ResponseViewModel {
    }
}