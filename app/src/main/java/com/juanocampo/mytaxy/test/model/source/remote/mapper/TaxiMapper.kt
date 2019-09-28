package com.juanocampo.mytaxy.test.model.source.remote.mapper

import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse
import io.reactivex.functions.Function

class TaxiMapper: Function<List<TaxiResponse>, List<Taxi>> {


    override fun apply(toParse: List<TaxiResponse>): List<Taxi> {
        if (toParse.isNullOrEmpty()) {
            return emptyList()
        }
        val list = ArrayList<Taxi>()
        toParse.forEach {
            list.add(it.toTaxi())
        }
        return list
    }

}