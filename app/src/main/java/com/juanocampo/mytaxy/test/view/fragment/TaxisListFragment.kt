package com.juanocampo.mytaxy.test.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.juanocampo.mytaxy.test.R
import com.juanocampo.mytaxy.test.di.AndroidInjectorUtils
import com.juanocampo.mytaxy.test.view.adapter.TaxiAdapter
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModel
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModelFactory
import kotlinx.android.synthetic.main.list_view.*
import javax.inject.Inject

class TaxisListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_view, container, false)
    }

    private lateinit var adapter: TaxiAdapter

    @Inject
    lateinit var viewModelFactory: TaxiViewModelFactory

    private lateinit var viewModel: TaxiViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidInjectorUtils.inject(this)
        initUIComponents()
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TaxiViewModel::class.java)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.taxiListLiveData.observe(this, Observer {
            it?.let { items ->
                progress.visibility = View.GONE
                adapter.addItems(items)
            }
        })
        viewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }


    private fun initUIComponents() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        taxList.layoutManager = manager
        adapter = TaxiAdapter()
        taxList.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(taxList)
    }

}