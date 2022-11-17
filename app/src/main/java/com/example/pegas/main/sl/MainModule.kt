package com.example.pegas.main.sl

import com.example.pegas.main.presentation.MainViewModel
import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module

class MainModule(private val core: Core) : Module<MainViewModel> {
    override fun viewModel() = MainViewModel(
        core.provideNavigation()
    )
}