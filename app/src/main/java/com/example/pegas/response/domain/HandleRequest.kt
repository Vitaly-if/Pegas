package com.example.pegas.response.domain

/**
 * @author Vitaly.N on 14.02.2023.
 */
interface HandleRequest {

    suspend fun handle(block: suspend () -> ForwardDocDomain): ForwardDocResult

    class Base(
        private val handleError: HandleError<String>,
    ) : HandleRequest {

        override suspend fun handle(block: suspend () -> ForwardDocDomain): ForwardDocResult = try {
           val forwardDocDomain = block.invoke() as ForwardDocDomain.Base
           ForwardDocResult.Success(forwardDocDomain)
        } catch (e: Exception) {
            ForwardDocResult.Failure(handleError.handle(e))
        }
    }
}