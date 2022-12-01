package com.example.pegas.response.domain

import com.example.pegas.response.data.ForwardDocData

interface ResponseRepository {
    suspend fun fetchForwardDoc(id : String): ForwardDocData
}