package com.example.pegas.response.presentation

import com.example.pegas.response.domain.ForwardDocDomain
import com.example.pegas.response.domain.ForwardDocUiMapper
import com.example.pegas.search.presentation.UiState
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Vitaly.N on 21.02.2023.
 */
class ForwardDocUiResultMapperTest : BaseTest() {

    @Test
    fun test_error() {
        val communications = TestCommunications()
        val mapper = ForwardDocUiResultMapper(communications, ForwardDocUiMapper())
        val emptyForwardDocDomain = ForwardDocDomain.Base("", "", "", "", "", "")

        mapper.map(emptyForwardDocDomain, "not empty message")

        assertEquals(ForwardDocUiState.ShowError("not empty message"),
            communications.stateCalledList[0])
    }

    @Test
    fun test_success() {
        val communications = TestCommunications()
        val mapper = ForwardDocUiResultMapper(communications, ForwardDocUiMapper())
        val simpleForwardDocDomain = ForwardDocDomain.Base("5", "12345", "1234", "", "", "")
        mapper.map(simpleForwardDocDomain, "")

        assertEquals(1, communications.timeShowList)
        assertEquals(ForwardDocUi("5", "12345", "1234", "", "", ""),
            communications.forwardDocUiList[0])
    }
}