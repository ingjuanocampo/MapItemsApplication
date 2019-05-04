package com.juanocampo.mytaxy.test.model.source.remote.domain

data class TaxiApiResponse(val poiList: List<TaxiResponse>?)

data class TaxiResponse(val id: Int?, val coordinate: Coordinate?)

data class Coordinate(val latitude: Float?, val longitude: Float?)