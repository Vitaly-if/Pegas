package com.example.pegas.search.presentation

import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

sealed class UiState {

    abstract fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText, buttonGo: Button)

    class Success : UiState() {

        override fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText, buttonGo: Button) {
            inputLayout.visibility = View.VISIBLE
            textInputEditText.visibility = View.VISIBLE
            buttonGo.visibility = View.VISIBLE
            return textInputEditText.setText("")
        }
    }
    class Gone : UiState() {
        override fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText, buttonGo: Button) {
            inputLayout.visibility = View.GONE
            textInputEditText.visibility = View.GONE
            buttonGo.visibility = View.GONE
        }
    }

    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean,
    ) : UiState() {
        override fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText, buttonGo: Button) {
            return with(inputLayout) {
                isErrorEnabled = errorEnabled
                error = message
            }
        }

    }

    data class ShowError(private val text: String) : AbstractError(text, true)

    class CleanError : AbstractError("", false)
}