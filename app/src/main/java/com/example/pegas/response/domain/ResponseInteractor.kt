package com.example.pegas.response.domain

import com.example.pegas.response.data.ForwardDocToDomain
import com.example.pegas.response.data.IdForwardDoc

interface ResponseInteractor {
    suspend fun fetchForwardDoc() : ForwardDocResult

    class Base(
        private val repository: ResponseRepository,
        private val idForwardDoc: IdForwardDoc.Read,
        private val mapperToDomain: ForwardDocToDomain
    ): ResponseInteractor {
        override suspend fun fetchForwardDoc(): ForwardDocResult {
            return ForwardDocResult.Success(repository.fetchForwardDoc(idForwardDoc.read())
                .map(mapperToDomain))
        }
    }
}