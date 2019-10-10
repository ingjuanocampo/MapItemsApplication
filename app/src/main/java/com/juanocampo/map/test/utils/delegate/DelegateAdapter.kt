package com.juanocampo.map.test.utils.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import juanocampo.myapplication.presentation.entitity.RecyclerViewType

interface DelegateAdapter<VH : RecyclerView.ViewHolder, VT : RecyclerViewType> {

    fun onCreateViewHolder(parent: ViewGroup): VH

    fun onBindViewHolder(viewHolder: VH, viewType: VT)
}