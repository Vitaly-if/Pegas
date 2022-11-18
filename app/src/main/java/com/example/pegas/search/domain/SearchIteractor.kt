package com.example.pegas.search.domain

import com.example.pegas.main.sl.ProvideIdForwardDoc
import com.example.pegas.response.data.IdForwardDoc

interface SearchIteractor {

    fun saveIdForwarDoc(id: String)

    class Base(private val idForwardDoc: IdForwardDoc.Save) : SearchIteractor {
        override fun saveIdForwarDoc(id: String) {
            idForwardDoc.save(id)
        }
    }

}