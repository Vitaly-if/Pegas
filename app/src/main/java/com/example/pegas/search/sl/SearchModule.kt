package com.example.pegas.search.sl

import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module
import com.example.pegas.search.domain.SearchIteractor
import com.example.pegas.search.presentation.ProgressCommunication
import com.example.pegas.search.presentation.SearchCommunication
import com.example.pegas.search.presentation.SearchStateCommunication
import com.example.pegas.search.presentation.SearchViewModel

class SearchModule(
    private val core: Core,
) : Module<SearchViewModel.Base> {
    override fun viewModel(): SearchViewModel.Base {
        val communication = SearchCommunication.Base(
            ProgressCommunication.Base(),
            SearchStateCommunication.Base()
        )

        return SearchViewModel.Base(
            communication,
            core.provideNavigation(),
            SearchIteractor.Base(core.provideIdForwardDoc())
        )
    }
}