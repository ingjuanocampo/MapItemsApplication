package com.juanocampo.map.test.data.source.remote

import com.juanocampo.map.test.data.source.remote.domain.TaxiResponse

interface IRemoteDataSource {

    fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<TaxiResponse>
}