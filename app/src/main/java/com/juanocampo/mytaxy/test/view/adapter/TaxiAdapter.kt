package com.juanocampo.mytaxy.test.view.adapter

import android.support.v4.util.SparseArrayCompat
import com.juanocampo.mytaxy.test.data.entity.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.BaseAdapter
import com.juanocampo.mytaxy.test.utils.delegate.appendDelegate

class TaxiAdapter(listener: TaxiDelegateAdapter.OnItemListListener): BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(Taxi.TAXI_ITEM.hashCode(), TaxiDelegateAdapter(listener))
    }
}