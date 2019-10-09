package juanocampo.myapplication.data.source.remote.mapper

import juanocampo.myapplication.data.source.remote.domain.TaxiResponse
import juanocampo.myapplication.domain.entity.Taxi
import java.util.*

class TaxiMapper:
    IMapper<List<TaxiResponse>, List<Taxi>> {

    init {
        println("Instances: ${this::class.java.name} ${this.hashCode()}")
    }

    override fun mapResponseToAppModel(toParse: List<TaxiResponse>): List<Taxi> {
        val list = ArrayList<Taxi>()
        toParse.forEach {
            list.add(it.toTaxi())
        }
        return list
    }
}