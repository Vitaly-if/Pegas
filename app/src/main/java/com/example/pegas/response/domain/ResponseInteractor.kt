package com.example.pegas.response.domain

import com.example.pegas.response.data.IdForwardDoc

interface ResponseInteractor {
    suspend fun fetchForwardDoc(): ForwardDocResult

    class Base(
        private val repository: ResponseRepository,
        private val idForwardDoc: IdForwardDoc.Read,
        private val handleDataRequest: HandleRequest,
    ) : ResponseInteractor {
        override suspend fun fetchForwardDoc(): ForwardDocResult =
            handleDataRequest.handle {
                repository.fetchForwardDoc(idForwardDoc.read())
            }
    }
}