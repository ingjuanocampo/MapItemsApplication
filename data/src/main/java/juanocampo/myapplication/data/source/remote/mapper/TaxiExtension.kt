package juanocampo.myapplication.data.source.remote.mapper

import juanocampo.myapplication.data.source.remote.domain.TaxiResponse
import juanocampo.myapplication.domain.entity.Coordinate
import juanocampo.myapplication.domain.entity.Taxi

fun TaxiResponse.toTaxi() =
    Taxi(
        id = this.id ?: 0,
        latLong = Coordinate(this.coordinate?.latitude ?: 0.0, this.coordinate?.longitude ?: 0.0)
    )