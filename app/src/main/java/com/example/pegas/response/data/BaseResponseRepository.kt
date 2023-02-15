package com.example.pegas.response.data

import com.example.pegas.response.data.cloud.ForwardDocCloudDataSourse
import com.example.pegas.response.data.cloud.ForwardDocDTO
import com.example.pegas.response.domain.ForwardDocDomain
import com.example.pegas.response.domain.ResponseRepository

class BaseResponseRepository(
    private val cloudDataSourse: ForwardDocCloudDataSourse,
    private val mapperToData: ForwardDocDTO.Mapper<ForwardDocData>,
    private val mapperToDomain: ForwardDocToDomain
) : ResponseRepository {

    override suspend fun fetchForwardDoc(id: String): ForwardDocDomain {
        val data = cloudDataSourse.fetchForvardDoc()
        val result = data.find {
            it.map(ForwardDocDTO.Mapper.Matcher(id))
        }
        return result?.map(mapperToData)?.map(mapperToDomain) ?: ForwardDocData("",
            "",
            "",
            "",
            "",
            "").map(mapperToDomain)
    }
}