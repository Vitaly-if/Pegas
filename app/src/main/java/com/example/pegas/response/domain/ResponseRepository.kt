package com.example.pegas.response.domain

interface ResponseRepository {
    suspend fun fetchForwardDoc(id: String): ForwardDocDomain
}