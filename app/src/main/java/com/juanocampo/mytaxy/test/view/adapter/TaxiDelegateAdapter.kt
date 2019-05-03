package com.juanocampo.mytaxy.test.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.juanocampo.mytaxy.test.R
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.DelegateAdapter

class TaxiDelegateAdapter: DelegateAdapter<TaxiDelegateAdapter.ViewHolder, Taxi>  {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: Taxi) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.taxi_item, viewGroup, false)) {

    }
}