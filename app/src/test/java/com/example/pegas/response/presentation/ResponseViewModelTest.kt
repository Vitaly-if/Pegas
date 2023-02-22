package com.example.pegas.response.presentation

import android.view.View
import com.example.pegas.main.presentation.DispatcherList
import com.example.pegas.main.presentation.ResourceProvider
import com.example.pegas.response.domain.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Vitaly.N on 21.02.2023.
 */
class ResponseViewModelTest : BaseTest() {

    private lateinit var navigation: TestNavigationCommunication
    private lateinit var viewModel: ResponseViewModel
    private lateinit var communications: TestCommunications
    private lateinit var interactorTest: TestResponseInteractor
    private lateinit var managerResources: TestManageResource

    @Before
    fun init() {
        navigation = TestNavigationCommunication()
        communications = TestCommunications()
        interactorTest = TestResponseInteractor()
        val detailsMapper = TestUiMapper()
        viewModel =
            ResponseViewModel.Base(
                navigation,
                HandleForwardDocRequest.Base(
                    TestDispatcherList(),
                    communications,
                    ForwardDocUiResultMapper(communications, ForwardDocUiMapper()),
                ),
                interactorTest,
                communications
            )
    }

    @Test
    fun `test init and re-init`() = runBlocking {
        interactorTest.changeExpectedResult(ForwardDocResult.Success())
        viewModel.fetchForwardDoc()
        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(1, interactorTest.fetchForwardDocCalled.size)

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(true, communications.stateCalledList[0] is ForwardDocUiState.Success)

        assertEquals(1, communications.forwardDocUiList.size)
        assertEquals(1, communications.timeShowList)

        interactorTest.changeExpectedResult(ForwardDocResult.Failure("no internet connection"))
        viewModel.fetchForwardDoc()

        assertEquals(View.VISIBLE, communications.progressCalledList[2])

        assertEquals(2, interactorTest.fetchForwardDocCalled.size)

        assertEquals(4, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[3])

        assertEquals(2, communications.stateCalledList.size)
        assertEquals(ForwardDocUiState.ShowError("no internet connection"),
            communications.stateCalledList[1])
    }

    @Test
    fun `test fetch forward doc`() = runBlocking {
        interactorTest.changeExpectedResult(ForwardDocResult.Success(ForwardDocDomain.Base("45",
            "0012345",
            "",
            "",
            "",
            "")))
        viewModel.fetchForwardDoc()
        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(ForwardDocResult.Success(ForwardDocDomain.Base("45",
            "0012345",
            "",
            "",
            "",
            "")), interactorTest.fetchForwardDocCalled[0])
    }

}

class TestResponseInteractor : ResponseInteractor {

    private var result: ForwardDocResult = ForwardDocResult.Success()

    val fetchForwardDocCalled = mutableListOf<ForwardDocResult>()

    fun changeExpectedResult(newResult: ForwardDocResult) {
        result = newResult
    }


    override suspend fun fetchForwardDoc(): ForwardDocResult {
        fetchForwardDocCalled.add(result)
        return result
    }

}

class TestDispatcherList : DispatcherList {
    private val dispatcher = TestCoroutineDispatcher()
    override fun io(): CoroutineDispatcher = dispatcher
    override fun ui(): CoroutineDispatcher = dispatcher

}

private class TestManageResource() : ResourceProvider {
    private var value = ""

    fun changeExpected(string: String) {
        value = string
    }

    override fun getString(id: Int): String = value

}

private class TestUiMapper : ForwardDocUi.Mapper<String> {
    override fun map(
        id: String,
        er: String,
        load: String,
        dateLoad: String,
        unload: String,
        dateUnLoad: String,
    ) = "$id $er $load $dateLoad $unload $dateUnLoad"

}