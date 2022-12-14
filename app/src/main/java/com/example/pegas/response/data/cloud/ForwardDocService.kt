package com.example.pegas.response.data.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ForwardDocService {

    @GET("forwards/")
    suspend fun fetchForwardDoc(): ResponseBody
}