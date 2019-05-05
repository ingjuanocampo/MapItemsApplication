package com.juanocampo.mytaxy.test.model.source.remote

import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse
import io.reactivex.Single

interface IRemoteDataSource {

    fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double):Single<List<TaxiResponse>>
}