package com.example.pegas.response.sl

import com.example.pegas.main.sl.Core
import com.example.pegas.main.sl.Module
import com.example.pegas.response.data.BaseResponseRepository
import com.example.pegas.response.data.ForwardDocCloudToData
import com.example.pegas.response.data.ForwardDocToDomain
import com.example.pegas.response.data.cloud.ForwardDocCloudDataSourse
import com.example.pegas.response.data.cloud.ForwardDocService
import com.example.pegas.response.domain.ForwardDocUiMapper
import com.example.pegas.response.domain.ResponseInteractor
import com.example.pegas.response.domain.ResponseRepository
import com.example.pegas.response.presentation.*

class ResponseModule(
    private val core: Core,
    private val provideRepository: ProvideResponseRepository,
) : Module<ResponseViewModel.Base> {
    override fun viewModel(): ResponseViewModel.Base {
        val repository = provideRepository.provideResponseRepository()
        val mapperToDomain = ForwardDocToDomain()
        val responseCommunication = ResponseCommunication.Base(
            ProgressCommunication.Base(),
            ResponseStateCommunication.Base(),
            ForwardDocUiCommunication.Base()
        )
        return ResponseViewModel.Base(core.provideNavigation(),
            HandleForwardDocRequest.Base(core.provideDispatchers(),
                responseCommunication,
                ForwardDocUiResultMapper(responseCommunication, ForwardDocUiMapper())),
            ResponseInteractor.Base(repository, core.provideIdForwardDoc(), mapperToDomain),
            responseCommunication)
    }
}

interface ProvideResponseRepository {
    fun provideResponseRepository(): ResponseRepository
    class Base(private val core: Core) : ProvideResponseRepository {
        override fun provideResponseRepository(): ResponseRepository {
            return BaseResponseRepository(ForwardDocCloudDataSourse.Base(core.service(
                ForwardDocService::class.java), core.provideMockGson(), core.provideIdForwardDoc()),
                ForwardDocCloudToData())
//            return BaseResponseRepository(ForwardDocCloudDataSourse.Mock(
//                core.provideResource().getResource(), core.provideMockGson(),
//                core.provideIdForwardDoc()), ForwardDocCloudToData()
//            )
        }
    }
}