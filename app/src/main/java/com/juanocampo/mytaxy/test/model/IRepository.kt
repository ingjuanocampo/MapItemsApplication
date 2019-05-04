package com.juanocampo.mytaxy.test.model

import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Taxi


interface IRepository {

    suspend fun requestTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): Resource<List<Taxi>>
}