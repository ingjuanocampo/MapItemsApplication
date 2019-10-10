package juanocampo.myapplication.data.source.remote

import juanocampo.myapplication.data.source.remote.domain.TaxiResponse

interface IRemoteDataSource {

    fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<TaxiResponse>
}