package com.juanocampo.mytaxy.test.model.source.remote.mapper

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse

fun TaxiResponse.toTaxi() =
    Taxi(id = this.id?: 0,  latLong = LatLng(this.coordinate?.latitude?: 0.0, this.coordinate?.longitude?: 0.0))