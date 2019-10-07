package juanocampo.myapplication.domain.repository

import juanocampo.myapplication.domain.entity.Taxi

interface IRepository {

    suspend fun requestTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<Taxi>
}