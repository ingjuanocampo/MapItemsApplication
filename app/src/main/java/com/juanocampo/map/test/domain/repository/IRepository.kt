package com.juanocampo.map.test.domain.repository

import com.juanocampo.map.test.data.entity.Taxi


interface IRepository {

    suspend fun requestTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<Taxi>
}