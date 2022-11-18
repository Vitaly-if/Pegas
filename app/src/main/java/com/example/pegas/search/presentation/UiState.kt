package com.example.pegas.search.presentation

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

sealed class UiState {

    abstract fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText)

    class Success : UiState() {

        override fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
            return textInputEditText.setText("")
        }
    }
    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean
    ) : UiState() {
        override fun apply(inputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
            return with(inputLayout) {
                isErrorEnabled = errorEnabled
                error = message
            }
        }
        data class ShowError(private val text: String) : AbstractError(text, true)
        class CleanError : AbstractError("", false)
    }

}