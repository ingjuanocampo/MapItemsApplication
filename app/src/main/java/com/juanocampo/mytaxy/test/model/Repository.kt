package com.juanocampo.mytaxy.test.model

import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.IRemoteDataSource
import com.juanocampo.mytaxy.test.model.source.remote.mapper.TaxiMapper
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
    ): Resource<List<Taxi>> {
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
    ): Resource<List<Taxi>> {
        return try {
            val fetchedItems = iRemoteDataSource.fetchTaxisByLocation(
                p1Lat,
                p1Lon,
                p2Lat,
                p2Lon
            )
            if (fetchedItems.isNullOrEmpty()) {
                Resource.error("could not load info, try later")
            } else {
                val mappedItems = iMapper.mapResponseToAppModel(fetchedItems)
                Resource.success(mappedItems)
            }
        } catch (e: Exception) {
            Resource.error(e.message ?: "Something went wrong ")
        }
    }
}