package com.example.pegas.response.data

import com.example.pegas.response.data.cloud.ForwardDocCloudDataSourse
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Vitaly.N on 02.02.2023.
 */
class BaseResponseRepositoryTest {
    private lateinit var repository: BaseResponseRepository
    private lateinit var cloudDataSourse: ForwardDocCloudDataSourse

    @Before
    fun setUp() {
        val gson = Gson()
        cloudDataSourse = MockUnit(gson)
        val mapper = ForwardDocCloudToData()
        repository = BaseResponseRepository(cloudDataSourse, mapper)
    }

    @Test
    fun test_fetch_forwarddoc() = runBlocking {
        val actual = repository.fetchForwardDoc("С011501")
        val expected = ForwardDocData("0",
            "С011501",
            "Улан-Удэ",
            "27.12.2021",
            "Чита",
            "28.12.2021")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_negative_forwarddoc() = runBlocking {
        val actual = repository.fetchForwardDoc("С011506")
        val expected = ForwardDocData("",
            "",
            "",
            "",
            "",
            "")
        assertEquals(expected, actual)
    }
    private class MockUnit(
        gson: Gson,
    ) : ForwardDocCloudDataSourse.Abstract(gson) {
        override suspend fun getDataAsString() =
            "[\n" +
                    "  {\n" +
                    "    \"id\": 0,\n" +
                    "    \"er\": \"С011501\",\n" +
                    "    \"dateload\": \"27.12.2021\",\n" +
                    "    \"load\": \"Улан-Удэ\",\n" +
                    "    \"dateunload\": \"28.12.2021\",\n" +
                    "    \"unload\": \"Чита\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"er\": \"006521\",\n" +
                    "    \"dateload\": \"27.12.2021\",\n" +
                    "    \"load\": \"Улан-Удэ\",\n" +
                    "    \"dateunload\": \"28.12.2021\",\n" +
                    "    \"unload\": \"Чита\"\n" +
                    "  }]"
    }

}