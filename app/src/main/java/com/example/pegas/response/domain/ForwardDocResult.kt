package com.example.pegas.response.domain

sealed class ForwardDocResult {
    interface Mapper<T> {
        fun map(doc: ForwardDocDomain, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val doc: ForwardDocDomain) : ForwardDocResult() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(doc, "")
        }
    }

    data class Failure(private val message: String) : ForwardDocResult() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(ForwardDocDomain("", "", "", "", "", ""), message)
        }
    }
}
