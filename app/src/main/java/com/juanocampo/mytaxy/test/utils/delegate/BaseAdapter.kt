package com.juanocampo.mytaxy.test.utils.delegate

import android.support.v4.util.SparseArrayCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.juanocampo.mytaxy.test.utils.delegate.model.RecyclerViewType

open class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var delegateAdapters: SparseArrayCompat<DelegateAdapter<RecyclerView.ViewHolder, RecyclerViewType>>
    var items: ArrayList<RecyclerViewType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters[viewType]?.onCreateViewHolder(parent)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = items[position]
        val delegateAdapter = delegateAdapters.get(viewType.getViewType())
        delegateAdapter?.onBindViewHolder(holder, viewType)
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun getItemCount() = items.size

    open fun addItems(updatedConversationMessages: List<RecyclerViewType>) {
        val diffResult = DiffUtil.calculateDiff(DelegateDiffCallback(items, updatedConversationMessages))

        if (updatedConversationMessages.isNullOrEmpty().not()) {
            items.clear()
            items.addAll(updatedConversationMessages)
        }
        diffResult.dispatchUpdatesTo(this)
    }


    open fun addItem(itemToAdd: RecyclerViewType) {
        val list = ArrayList<RecyclerViewType>()
        list.add(itemToAdd)
        addItems(list)
    }
}