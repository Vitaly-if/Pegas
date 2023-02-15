package com.example.pegas.response.presentation

import com.example.pegas.response.domain.ForwardDocDomain
import com.example.pegas.response.domain.ForwardDocResult

/**
 * @author Vitaly.N on 30.11.2022.
 */
class ForwardDocUiResultMapper(
    private val communication: ResponseCommunication,
    private val mapper: ForwardDocDomain.Mapper<ForwardDocUi>,
) : ForwardDocResult.Mapper<Unit> {

    override fun map(doc: ForwardDocDomain.Base, errorMessage: String) {

        if (errorMessage.isEmpty()) {
            communication.show(doc.map(mapper))
            communication.showState(ForwardDocUiState.Success())
        } else communication.showState(ForwardDocUiState.ShowError(errorMessage))
    }


}