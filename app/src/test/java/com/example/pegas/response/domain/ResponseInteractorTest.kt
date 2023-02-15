package com.example.pegas.response.domain

import com.example.pegas.main.presentation.ResourceProvider
import com.example.pegas.response.data.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * @author Vitaly.N on 02.02.2023.
 */
class ResponseInteractorTest {

    private lateinit var interactor: ResponseInteractor
    private lateinit var repository: TestBaseResponseRepository
    private lateinit var idForwardDoc: IdForwardDoc.Base
    private lateinit var managerResources: TestManageResource

    @Before
    fun setUp() {
        idForwardDoc = IdForwardDoc.Base()
        managerResources = TestManageResource()
        repository = TestBaseResponseRepository()
        interactor = ResponseInteractor.Base(repository, idForwardDoc, HandleRequest.Base(
            HandleError.Base(managerResources), repository, idForwardDoc
        ))
    }

    @Test
    fun test_success() = runBlocking {
        repository.changeExpectedForwardDoc()
        idForwardDoc.save("С011501")
        val actual = interactor.fetchForwardDoc()
        val expected = ForwardDocResult.Success(ForwardDocDomain.Base("0",
            "С011501",
            "Улан-Удэ",
            "27.12.2021",
            "Чита",
            "28.12.2021"))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        idForwardDoc.save("С011503")
        managerResources.changeExpected("Ошибка эр не найдена")
        repository.expectingErrorGetForwardDoc(true)
        val actual = interactor.fetchForwardDoc()
        val expected = ForwardDocResult.Failure("Ошибка эр не найдена")
        assertEquals(expected, actual)
    }


    private class TestBaseResponseRepository : ResponseRepository {
        private var errorWhileForwardDoc = false
        private var forwardDocDomain = ForwardDocDomain.Base("",
            "",
            "",
            "",
            "",
            "")

        fun expectingErrorGetForwardDoc(error: Boolean) {
            errorWhileForwardDoc = error
        }

        fun changeExpectedForwardDoc() {
            forwardDocDomain = ForwardDocDomain.Base("0",
                "С011501",
                "Улан-Удэ",
                "27.12.2021",
                "Чита",
                "28.12.2021")
        }

        override suspend fun fetchForwardDoc(id: String): ForwardDocDomain {

            if (errorWhileForwardDoc)
                throw IllegalStateException()
            return forwardDocDomain
        }
    }

    private class TestManageResource() : ResourceProvider {
        private var value = ""

        fun changeExpected(string: String) {
            value = string
        }

        override fun getString(id: Int): String = value

    }

}