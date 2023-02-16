package com.example.pegas.response.presentation

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView


/**
 * @author Vitaly.N on 30.11.2022.
 */
sealed class ForwardDocUiState {

    abstract fun apply(
        erView: TextView,
        loadView: TextView,
        loadDateView: TextView,
        unLoadView: TextView,
        unLoadDateView: TextView,
        cardTopView: CardView,
        cardCenterView: CardView,
        errorView: TextView,
        textTitleView: TextView,
    )

    class Success : ForwardDocUiState() {

        override fun apply(
            erView: TextView,
            loadView: TextView,
            loadDateView: TextView,
            unLoadView: TextView,
            unLoadDateView: TextView,
            cardTopView: CardView,
            cardCenterView: CardView,
            errorView: TextView,
            textTitleView: TextView,
        ) {
            erView.visibility = View.VISIBLE
            loadView.visibility = View.VISIBLE
            loadDateView.visibility = View.VISIBLE
            unLoadView.visibility = View.VISIBLE
            unLoadDateView.visibility = View.VISIBLE
            cardTopView.visibility = View.VISIBLE
            cardCenterView.visibility = View.VISIBLE
            errorView.visibility = View.GONE
            textTitleView.visibility = View.VISIBLE

        }
    }

    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean,
    ) : ForwardDocUiState() {
        override fun apply(
            erView: TextView,
            loadView: TextView,
            loadDateView: TextView,
            unLoadView: TextView,
            unLoadDateView: TextView,
            cardTopView: CardView,
            cardCenterView: CardView,
            errorView: TextView,
            textTitleView: TextView,

            ) {
            erView.visibility = View.GONE
            loadView.visibility = View.GONE
            loadDateView.visibility = View.GONE
            unLoadView.visibility = View.GONE
            unLoadDateView.visibility = View.GONE
            cardTopView.visibility = View.GONE
            cardCenterView.visibility = View.GONE
            errorView.visibility = View.VISIBLE
            errorView.text = message

        }
    }

    data class ShowError(private val text: String) : AbstractError(text, true)

    class CleanError : AbstractError("", false)
}