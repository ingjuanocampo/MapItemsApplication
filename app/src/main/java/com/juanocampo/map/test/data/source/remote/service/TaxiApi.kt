package com.juanocampo.map.test.data.source.remote.service

import com.juanocampo.map.test.data.source.remote.domain.TaxiApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TaxiApi {

    @GET("/")
    fun fetchTaxisByLocation(@Query("p1Lat") p1Lat: String,
                    @Query("p1Lon") p1Lon: String,
                    @Query("p2Lat") p2Lat: String,
                    @Query("p2Lon") p2Lon: String): Call<TaxiApiResponse>
}