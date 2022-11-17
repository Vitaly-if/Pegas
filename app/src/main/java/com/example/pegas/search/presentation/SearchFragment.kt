package com.example.pegas.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pegas.R
import com.example.pegas.main.presentation.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel.Base>() {
    override val viewModelClass: Class<SearchViewModel.Base> = SearchViewModel.Base::class.java

    override val layoutID: Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonGo = view.findViewById<Button>(R.id.buttonGo)
        buttonGo.setOnClickListener{
            viewModel.showResponse()
        }
    }

}