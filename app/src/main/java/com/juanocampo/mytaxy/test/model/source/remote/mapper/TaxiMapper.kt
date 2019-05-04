package com.juanocampo.mytaxy.test.model.source.remote.mapper

import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse

class TaxiMapper: IMapper<List<TaxiResponse>, List<Taxi>> {

    override fun mapResponseToAppModel(toParse: List<TaxiResponse>): List<Taxi> {
        val list = ArrayList<Taxi>()
        toParse.forEach {
            list.add(it.toTaxi())
        }
        return list
    }
}