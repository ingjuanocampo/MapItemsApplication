package com.juanocampo.mytaxy.test.model

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Taxi

import io.reactivex.Observable


interface IRepository {

    fun requestTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): Observable<Resource<HashMap<LatLng, Taxi>>>
}