package com.juanocampo.mytaxy.test.domain

import com.juanocampo.mytaxy.test.data.entity.Taxi


interface IRepository {

    suspend fun requestTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<Taxi>
}