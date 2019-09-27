package com.juanocampo.map.test.data

import com.juanocampo.map.test.data.entity.Taxi
import com.juanocampo.map.test.data.source.remote.IRemoteDataSource
import com.juanocampo.map.test.data.source.remote.mapper.TaxiMapper
import com.juanocampo.map.test.domain.IRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val iRemoteDataSource: IRemoteDataSource,
    private val iMapper: TaxiMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IRepository {

    override suspend fun requestTaxisByLocation(
        p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double
    ): List<Taxi> {
        return withContext(ioDispatcher) {
            return@withContext fetchTaxisFromRemote(
                p1Lat,
                p1Lon,
                p2Lat,
                p2Lon
            )
        }
    }

    private fun fetchTaxisFromRemote(
        p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double
    ): List<Taxi> {
            val fetchedItems = iRemoteDataSource.fetchTaxisByLocation(
                p1Lat,
                p1Lon,
                p2Lat,
                p2Lon
            )
            check(!fetchedItems.isNullOrEmpty()) { "Could not get valid information" }
        return iMapper.mapResponseToAppModel(fetchedItems)
    }
}