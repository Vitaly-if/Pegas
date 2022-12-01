package com.example.pegas.response.data

import com.example.pegas.response.domain.ForwardDocDomain

class ForwardDocToDomain : ForwardDocData.Mapper<ForwardDocDomain> {
    override fun map(
        id: String, er: String, load: String, dateLoad: String,
        unload: String,
        dateUnLoad: String
    ): ForwardDocDomain {
        return ForwardDocDomain(id, er, load, dateLoad, unload, dateUnLoad)
    }
}