package com.example.pegas.response.presentation


/**
 * @author Vitaly.N on 30.11.2022.
 */
sealed class ForwardDocUiState {

    abstract fun apply()

    class Success : ForwardDocUiState() {

        override fun apply() {

        }
    }

    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean,
    ) : ForwardDocUiState() {
        override fun apply() {

        }
    }

    data class ShowError(private val text: String) : AbstractError(text, true)

    class CleanError : AbstractError("", false)
}