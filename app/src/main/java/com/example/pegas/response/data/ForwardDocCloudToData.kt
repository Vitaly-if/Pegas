package com.example.pegas.response.data

import com.example.pegas.response.data.cloud.ForwardDocDTO

class ForwardDocCloudToData : ForwardDocDTO.Mapper<ForwardDocData> {
    override fun map(
        id: String, er: String, load: String, dateLoad: String,
        unload: String,
        dateUnLoad: String,
    ) =
        ForwardDocData(id, er, load, dateLoad, unload, dateUnLoad)
}