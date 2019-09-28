package com.juanocampo.mytaxy.test.model.source.remote

import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse
import com.juanocampo.mytaxy.test.model.source.remote.service.TaxiApi
import io.reactivex.Single

class RemoteDataSource(private val api: TaxiApi): IRemoteDataSource {

     override fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): Single<List<TaxiResponse>> {
         return api.fetchTaxisByLocation(p1Lat = p1Lat.toString(), p1Lon =  p1Lon.toString(), p2Lat = p2Lat.toString(), p2Lon = p2Lon.toString()).map {
            return@map it.poiList ?: emptyList()
        }
    }

}