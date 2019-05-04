package com.juanocampo.mytaxy.test.view.adapter

import android.support.v4.util.SparseArrayCompat
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.BaseAdapter
import com.juanocampo.mytaxy.test.utils.delegate.appendDelegate

class TaxiAdapter: BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(Taxi.TAXI_ITEM.hashCode(), TaxiDelegateAdapter())
    }
}