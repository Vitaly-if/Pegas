package com.example.pegas.response.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.example.pegas.R
import com.example.pegas.main.presentation.BaseFragment

class ResponseFragment(
) : BaseFragment<ResponseViewModel.Base>() {
    override val viewModelClass = ResponseViewModel.Base::class.java
    override val layoutID = R.layout.fragment_response

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val erView = view.findViewById<TextView>(R.id.erView)
        val textTitleView = view.findViewById<TextView>(R.id.textTitleView2)
        val loadView = view.findViewById<TextView>(R.id.loadView)
        val loadDateView = view.findViewById<TextView>(R.id.loadDateView)
        val upLoadView = view.findViewById<TextView>(R.id.upLoadView)
        val upLoadDateView = view.findViewById<TextView>(R.id.upLoadDateView)
        val errorMessageView = view.findViewById<TextView>(R.id.errorMessageTextView)
        val progressBar = view.findViewById<ProgressBar>(R.id.ProgressBar)
        val cardTopView = view.findViewById<CardView>(R.id.card_top)
        val cardCenterView = view.findViewById<CardView>(R.id.card_response)

        val mapper = CardForwardDocUi(erView, loadView, loadDateView, upLoadView, upLoadDateView)
        viewModel.fetchForwardDoc()

        viewModel.observeForwardDocUi(this, Observer {
            it.map(mapper)
        })

        viewModel.observerState(this, Observer {
            it.apply(erView, loadView, loadDateView, upLoadView, upLoadDateView, cardTopView, cardCenterView, errorMessageView, textTitleView)
        })

        viewModel.observerProgress(this, Observer {
            progressBar.visibility = it
        })
    }
}