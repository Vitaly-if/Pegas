package com.example.pegas.response.data.cloud

import android.content.res.Resources
import com.example.pegas.R
import com.example.pegas.response.data.IdForwardDoc
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

interface ForwardDocCloudDataSourse {

    suspend fun fetchForvardDoc(): List<ForwardDocDTO>

    abstract class Abstract(private val gson: Gson) : ForwardDocCloudDataSourse {
        override suspend fun fetchForvardDoc(): List<ForwardDocDTO> = gson.fromJson(
            getDataAsString(),
            object : TypeToken<List<ForwardDocDTO>>() {}.type
        )
        protected abstract suspend fun getDataAsString(): String
    }
    class Base(
        private val service: ForwardDocService,
        gson: Gson,
        private val idForwardDoc: IdForwardDoc.Read
    ) : ForwardDocCloudDataSourse.Abstract(gson) {
        override suspend fun getDataAsString(): String = service.fetchForwardDoc(idForwardDoc.read()).string()
    }

    class Mock(
        private val resources: Resources,
        gson: Gson,
        private val idForwardDoc: IdForwardDoc.Read
    ) : ForwardDocCloudDataSourse.Abstract(gson) {
        override suspend fun getDataAsString() =
            resources.openRawResource(R.raw.forwarddoc).bufferedReader()
                .use(BufferedReader::readText)
    }
}