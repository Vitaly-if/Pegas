package com.example.pegas.response.data.cloud

import com.google.gson.annotations.SerializedName

data class ForwardDocDTO(
    @SerializedName("id") private val id: String,
    @SerializedName("er") private val er: String,
    @SerializedName("load") private val load: String,
    @SerializedName("dateload") private val dateLoad: String,
    @SerializedName("unload") private val unload: String,
    @SerializedName("dateunload") private val dateUnLoad: String,
) {
    interface Mapper<T> {
        fun map(
            id: String,
            er: String,
            load: String,
            dateLoad: String,
            unload: String,
            dateUnLoad: String
        ): T

        class Matcher(private val er: String) : Mapper<Boolean> {
            override fun map(
                id: String, er: String, load: String, dateLoad: String,
                unload: String,
                dateUnLoad: String
            ) = this.er == er
        }
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, er, load, dateLoad, unload, dateUnLoad)

}

