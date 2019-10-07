package com.juanocampo.map.test.view.adapter

import android.support.v4.util.SparseArrayCompat
import com.juanocampo.map.test.presentation.entitity.TaxiViewType
import com.juanocampo.map.test.utils.delegate.BaseAdapter
import com.juanocampo.map.test.utils.delegate.appendDelegate

class TaxiAdapter(listener: TaxiDelegateAdapter.OnItemListListener): BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(TaxiViewType.TAXI_ITEM.hashCode(), TaxiDelegateAdapter(listener))
    }
}