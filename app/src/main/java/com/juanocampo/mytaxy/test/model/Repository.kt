package com.juanocampo.mytaxy.test.model

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.IRemoteDataSource
import com.juanocampo.mytaxy.test.model.source.remote.mapper.TaxiMapper
import io.reactivex.Observable

class Repository(
    private val iRemoteDataSource: IRemoteDataSource,
    private val taxiResponseMapper: TaxiMapper
) : IRepository {

    override fun requestTaxisByLocation(
        p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double
    ): Observable<Resource<HashMap<LatLng, Taxi>>> {

        return iRemoteDataSource.fetchTaxisByLocation(
            p1Lat,
            p1Lon,
            p2Lat,
            p2Lon
        ).toObservable()
            .map(taxiResponseMapper)
            .map {listItems ->
                return@map if (listItems.isNullOrEmpty()) {
                    Resource.error("could not load info, try later")
                } else {
                    val mapItems: HashMap<LatLng, Taxi> = HashMap()
                    listItems.forEach {
                        mapItems[it.latLong] = it
                    }
                    Resource.success(mapItems)
                }
        }
    }
}