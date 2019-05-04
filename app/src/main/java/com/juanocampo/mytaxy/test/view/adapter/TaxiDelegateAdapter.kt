package com.juanocampo.mytaxy.test.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.juanocampo.mytaxy.test.R
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.DelegateAdapter

class TaxiDelegateAdapter(val listener: OnItemListListener): DelegateAdapter<TaxiDelegateAdapter.ViewHolder, Taxi>  {

    interface OnItemListListener {
        fun onClickedItem(taxi: Taxi)
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: Taxi) {
        viewHolder.bind(viewType, listener)
    }

    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.taxi_item, viewGroup, false)) {

        private val title = itemView.findViewById<TextView>(R.id.cab_id)

        fun bind(taxi: Taxi, listener: OnItemListListener) {
            title.text = "Cab id: ${taxi.id}"
            itemView.setOnClickListener {
                listener.onClickedItem(taxi)
            }
        }
    }

}