package com.example.pegas.response.presentation

import android.widget.TextView
import androidx.cardview.widget.CardView

data class ForwardDocUi(
    private val id: String,
    private val er: String,
    private val load: String,
    private val dateLoad: String,
    private val unload: String,
    private val dateUnLoad: String,
) {
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, er, load, dateLoad, unload, dateUnLoad)

    interface Mapper<T> {

        fun map(
            id: String, er: String, load: String, dateLoad: String,
            unload: String,
            dateUnLoad: String,
        ): T

    }
}

class CardForwardDocUi(
    private val erView: TextView,
    private val loadView: TextView,
    private val loadDateView: TextView,
    private val unLoadView: TextView,
    private val unLoadDateView: TextView,
) : ForwardDocUi.Mapper<Unit> {
    override fun map(
        id: String, er: String, load: String, dateLoad: String,
        unload: String,
        dateUnLoad: String
    ) {
        erView.text = er
        loadView.text = load
        loadDateView.text = dateLoad
        unLoadView.text = unload
        unLoadDateView.text = dateUnLoad

    }
}
