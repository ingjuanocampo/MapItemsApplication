package juanocampo.myapplication.data.source.remote

import juanocampo.myapplication.data.source.remote.domain.TaxiResponse
import juanocampo.myapplication.data.source.remote.service.TaxiApi
import java.util.Collections.emptyList


class RemoteDataSource(private val api: TaxiApi):
    IRemoteDataSource {

    override fun fetchTaxisByLocation(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double): List<TaxiResponse> {
        val request = api.fetchTaxisByLocation(p1Lat = p1Lat.toString(), p1Lon =  p1Lon.toString(), p2Lat = p2Lat.toString(), p2Lon = p2Lon.toString())
        val apiResponse = request.execute()
        return if (apiResponse.isSuccessful && apiResponse.body()?.poiList != null) {
            apiResponse.body()?.poiList!!
        } else {
            emptyList()
        }
    }

}