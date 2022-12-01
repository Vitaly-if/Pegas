package com.example.pegas.response.presentation

import android.view.View
import com.example.pegas.main.presentation.DispatcherList
import com.example.pegas.response.domain.ForwardDocResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Vitaly.N on 30.11.2022.
 */
interface HandleForwardDocRequest {
    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> ForwardDocResult,
    )

    class Base(
        private val dispatchers: DispatcherList,
        private val communications: ResponseCommunication,
        private val forwardDocResultMapper: ForwardDocResult.Mapper<Unit>,
    ) : HandleForwardDocRequest {

        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> ForwardDocResult) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.showProgress(View.GONE)
                result.map(forwardDocResultMapper)

            }
        }
    }
}