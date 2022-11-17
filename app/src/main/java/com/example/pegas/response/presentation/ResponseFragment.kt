package com.example.pegas.response.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pegas.R
import com.example.pegas.main.presentation.BaseFragment

class ResponseFragment(
) : BaseFragment<ResponseViewModel.Base>() {
    override val viewModelClass = ResponseViewModel.Base::class.java
    override val layoutID = R.layout.fragment_response

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}