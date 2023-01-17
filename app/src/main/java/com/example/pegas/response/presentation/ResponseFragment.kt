package com.example.pegas.response.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
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
        val loadView = view.findViewById<TextView>(R.id.loadView)
        val loadDateView = view.findViewById<TextView>(R.id.loadDateView)
        val upLoadView = view.findViewById<TextView>(R.id.uploadView)
        val upLoadDateView = view.findViewById<TextView>(R.id.dateUpLoadView)
        val errorMessageView = view.findViewById<TextView>(R.id.errorMessageTextView)
        val progressBar = view.findViewById<ProgressBar>(R.id.ProgressBar)

        val mapper = CardForwardDocUi(erView, loadView, loadDateView, upLoadView, upLoadDateView)
        viewModel.fetchForwardDoc()

        viewModel.observeForwardDocUi(this, Observer {
            it.map(mapper)
            Log.i("Vit", "observer ${it.toString()}")
        })

        viewModel.observerState(this, Observer {
            it.apply(erView, loadView, loadDateView, upLoadView, upLoadDateView,errorMessageView)
        })

        viewModel.observerProgress(this, Observer {
            progressBar.visibility = it
        })
    }
}