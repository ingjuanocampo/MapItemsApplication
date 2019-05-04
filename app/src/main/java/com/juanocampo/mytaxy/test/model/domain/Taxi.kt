package com.juanocampo.mytaxy.test.model.domain

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.utils.delegate.model.RecyclerViewType

data class Taxi(val id: Int, val latLong: LatLng): RecyclerViewType {

    override fun getDelegateId() = id

    override fun getViewType() = TAXI_ITEM.hashCode()

    companion object {
        const val TAXI_ITEM = "TAXI_delegate_item"
    }
}