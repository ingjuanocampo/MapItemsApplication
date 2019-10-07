package com.juanocampo.map.test.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.juanocampo.map.test.R
import com.juanocampo.map.test.presentation.entitity.TaxiViewType
import com.juanocampo.map.test.utils.delegate.DelegateAdapter

class TaxiDelegateAdapter(val listener: OnItemListListener): DelegateAdapter<TaxiDelegateAdapter.ViewHolder, TaxiViewType>  {

    interface OnItemListListener {
        fun onClickedItem(taxi: TaxiViewType)
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: TaxiViewType) {
        viewHolder.bind(viewType, listener)
    }

    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.taxi_item, viewGroup, false)) {

        private val title = itemView.findViewById<TextView>(R.id.cab_id)

        fun bind(taxi: TaxiViewType, listener: OnItemListListener) {
            title.text = "Cab id: ${taxi.id}"
            itemView.setOnClickListener {
                listener.onClickedItem(taxi)
            }
        }
    }

}