package com.juanocampo.map.test.utils.delegate

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import juanocampo.myapplication.presentation.entitity.RecyclerViewType

@Suppress("UNCHECKED_CAST")
fun <E : DelegateAdapter<RecyclerView.ViewHolder, RecyclerViewType>> SparseArrayCompat<E>.appendDelegate(
    delegateId: Int,
    delegate: DelegateAdapter<out RecyclerView.ViewHolder, out RecyclerViewType>
) {
    this.append(delegateId, delegate as E)
}