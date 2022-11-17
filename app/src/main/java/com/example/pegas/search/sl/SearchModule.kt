package com.example.pegas.search.sl

import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module
import com.example.pegas.main.sl.ProvideViewModel
import com.example.pegas.search.presentation.SearchViewModel

class SearchModule(
    private val core: Core,

) : Module<SearchViewModel.Base> {
    override fun viewModel(): SearchViewModel.Base {
        return SearchViewModel.Base(core.provideNavigation())
    }

}