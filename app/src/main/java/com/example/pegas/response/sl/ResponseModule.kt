package com.example.pegas.response.sl

import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module
import com.example.pegas.response.data.BaseResponseRepository
import com.example.pegas.response.data.ForwardDocCloudToData
import com.example.pegas.response.data.ForwardDocToDomain
import com.example.pegas.response.data.cloud.ForwardDocCloudDataSourse
import com.example.pegas.response.data.cloud.ForwardDocService
import com.example.pegas.response.domain.*
import com.example.pegas.response.presentation.*

class ResponseModule(
    private val core: Core,
    private val provideRepository: ProvideResponseRepository,
) : Module<ResponseViewModel.Base> {
    override fun viewModel(): ResponseViewModel.Base {
        val repository = provideRepository.provideResponseRepository()
        val responseCommunication = ResponseCommunication.Base(
            ProgressCommunication.Base(),
            ResponseStateCommunication.Base(),
            ForwardDocUiCommunication.Base()
        )
        return ResponseViewModel.Base(core.provideNavigation(),
            HandleForwardDocRequest.Base(core.provideDispatchers(),
                responseCommunication,
                ForwardDocUiResultMapper(responseCommunication, ForwardDocUiMapper())),
            ResponseInteractor.Base(repository, core.provideIdForwardDoc(),
                HandleRequest.Base(
                    HandleError.Base(core.provideResource()),
                )),
            responseCommunication)
    }
}

interface ProvideResponseRepository {
    fun provideResponseRepository(): ResponseRepository
    class Base(private val core: Core) : ProvideResponseRepository {
        override fun provideResponseRepository(): ResponseRepository {
            return BaseResponseRepository(ForwardDocCloudDataSourse.Base(core.service(
                ForwardDocService::class.java), core.provideMockGson()),
                ForwardDocCloudToData(), ForwardDocToDomain())
        }
    }
}