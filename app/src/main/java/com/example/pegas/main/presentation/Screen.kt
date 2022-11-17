package com.example.pegas.main.presentation

import com.example.pegas.response.presentation.ResponseFragment
import com.example.pegas.search.presentation.SearchFragment

sealed class Screen {
    abstract fun fragment(): Class<out BaseFragment<*>>

    object Responce : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = ResponseFragment::class.java
    }

    object Search : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = SearchFragment::class.java
    }
}