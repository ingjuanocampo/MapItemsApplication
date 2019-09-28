package com.juanocampo.map.test.data.source.remote.mapper

import com.juanocampo.map.test.data.entity.Taxi
import com.juanocampo.map.test.data.source.remote.domain.TaxiResponse

class TaxiMapper:
    IMapper<List<TaxiResponse>, List<Taxi>> {

    override fun mapResponseToAppModel(toParse: List<TaxiResponse>): List<Taxi> {
        val list = ArrayList<Taxi>()
        toParse.forEach {
            list.add(it.toTaxi())
        }
        return list
    }
}