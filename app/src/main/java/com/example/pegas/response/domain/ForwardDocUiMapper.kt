package com.example.pegas.response.domain

import com.example.pegas.response.presentation.ForwardDocUi

class ForwardDocUiMapper : ForwardDocDomain.Mapper<ForwardDocUi> {
    override fun map(
        id: String, er: String, load: String, dateLoad: String,
        unload: String,
        dateUnLoad: String,
    ): ForwardDocUi {
        return ForwardDocUi(id, er, load, dateLoad, unload, dateUnLoad)
    }
}