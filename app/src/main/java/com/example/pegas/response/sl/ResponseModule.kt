package com.example.pegas.response.sl

import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module
import com.example.pegas.response.domain.ResponseRepository
import com.example.pegas.response.presentation.ResponseViewModel

class ResponseModule(
    private val core: Core,
    private val provideRepository: ProvideResponseRepository
) : Module<ResponseViewModel.Base> {
    override fun viewModel(): ResponseViewModel.Base {
return ResponseViewModel.Base(core.provideNavigation())
    }
}
interface ProvideResponseRepository {
    fun provideResponseRepository(): ResponseRepository
    class Base(private val core: Core) : ProvideResponseRepository {
        override fun provideResponseRepository(): ResponseRepository {
            TODO("Not yet implemented")
        }
    }
}