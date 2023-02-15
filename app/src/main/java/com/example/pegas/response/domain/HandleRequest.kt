package com.example.pegas.response.domain

import com.example.pegas.response.data.IdForwardDoc

/**
 * @author Vitaly.N on 14.02.2023.
 */
interface HandleRequest {

    suspend fun handle(block: suspend () -> Unit): ForwardDocResult

    class Base(
        private val handleError: HandleError<String>,
        private val repository: ResponseRepository,
        private val idForwardDoc: IdForwardDoc.Read,
    ) : HandleRequest {

        override suspend fun handle(block: suspend () -> Unit): ForwardDocResult = try {
            block.invoke()
            ForwardDocResult.Success(repository.fetchForwardDoc(idForwardDoc.read()) as ForwardDocDomain.Base)
        } catch (e: Exception) {
            ForwardDocResult.Failure(handleError.handle(e))
        }
    }
}