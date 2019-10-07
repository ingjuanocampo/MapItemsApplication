package com.juanocampo.map.test.presentation.entitity

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.map.test.utils.delegate.model.RecyclerViewType

class TaxiViewType(val id: Int, val latLong: LatLng): RecyclerViewType {

    override fun getDelegateId() = id

    override fun getViewType() = TAXI_ITEM.hashCode()

    companion object {
        const val TAXI_ITEM = "TAXI_delegate_item"
    }
}