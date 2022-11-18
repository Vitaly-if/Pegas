package com.example.pegas.search.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import com.example.pegas.R
import com.example.pegas.main.presentation.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SearchFragment : BaseFragment<SearchViewModel.Base>() {
    override val viewModelClass: Class<SearchViewModel.Base> = SearchViewModel.Base::class.java

    override val layoutID: Int = R.layout.fragment_search

    private lateinit var inputEditText: TextInputEditText

    private val watcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(p0: Editable?) = viewModel.clearError()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
        inputEditText = view.findViewById(R.id.editText)
        inputEditText.addTextChangedListener(watcher)
        val buttonGo = view.findViewById<Button>(R.id.buttonGo)

        buttonGo.setOnClickListener {
            viewModel.showResponse(inputEditText.text.toString())
        }
        viewModel.observerState(this) {
            it.apply(inputLayout, inputEditText, buttonGo)
        }

    }

    abstract class SimpleTextWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun afterTextChanged(p0: Editable?) = Unit
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }
}