package com.example.pegas.response.domain

import com.example.pegas.R
import com.example.pegas.main.presentation.ResourceProvider
import java.net.ConnectException

/**
 * @author Vitaly.N on 14.02.2023.
 */
interface HandleError<T> {

    fun handle(e: Exception): T

    class Base(private val manageResources: ResourceProvider) : HandleError<String> {

        override fun handle(e: Exception) = manageResources.getString(
            when (e) {
                is ConnectException -> R.string.internet_not_connection
                else -> R.string.service_is_unavailable
            }
        )
    }
}