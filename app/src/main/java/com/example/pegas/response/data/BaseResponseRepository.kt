package com.example.pegas.response.data

import com.example.pegas.response.data.cloud.ForwardDocCloudDataSourse
import com.example.pegas.response.data.cloud.ForwardDocDTO
import com.example.pegas.response.domain.ResponseRepository

class BaseResponseRepository(
    private val cloudDataSourse: ForwardDocCloudDataSourse,
    private val mapperToData: ForwardDocDTO.Mapper<ForwardDocData>,
) : ResponseRepository {

    override suspend fun fetchForwardDoc(id: String): ForwardDocData {
        val data = cloudDataSourse.fetchForvardDoc()
        val result = data.find {
            it.map(ForwardDocDTO.Mapper.Matcher(id))
        }
        return result?.map(mapperToData) ?: ForwardDocData("",
            "",
            "",
            "",
            "",
            "")
    }
}