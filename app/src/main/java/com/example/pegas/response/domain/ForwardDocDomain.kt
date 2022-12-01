package com.example.pegas.response.domain

data class ForwardDocDomain(
    private val id: String,
    private val er: String,
    private val load: String,
    private val dateLoad: String,
    private val unload: String,
    private val dateUnLoad: String,
) {
    interface Mapper<T> {
        fun map(
            id: String, er: String, load: String, dateLoad: String,
            unload: String,
            dateUnLoad: String,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, er, load, dateLoad, unload, dateUnLoad)
}
