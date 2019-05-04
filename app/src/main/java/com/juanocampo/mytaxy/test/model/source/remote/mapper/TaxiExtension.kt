package com.juanocampo.mytaxy.test.model.source.remote.mapper

import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse

fun TaxiResponse.toTaxi() =
    Taxi(id = this.id?: 0, lat = this.coordinate?.latitude?: 0f, lng = this.coordinate?.longitude?: 0f)