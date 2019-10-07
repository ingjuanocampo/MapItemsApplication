package juanocampo.myapplication.data

import juanocampo.myapplication.data.source.remote.IRemoteDataSource
import juanocampo.myapplication.data.source.remote.mapper.TaxiMapper
import juanocampo.myapplication.domain.entity.Taxi
import juanocampo.myapplication.domain.repository.IRepository
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