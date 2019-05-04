package com.juanocampo.mytaxy.test.model.source.remote

import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse

interface IRemoteDataSource {

    fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<TaxiResponse>
}