package com.juanocampo.mytaxy.test.viewmodel

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import android.support.v7.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.IRepository
import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Status
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.model.RecyclerViewType
import kotlinx.coroutines.*

class TaxiViewModel(private val iRepository: IRepository,
                    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val taxiMapLiveData = MutableLiveData<HashMap<LatLng, RecyclerViewType>>()

    val itemFocused = MutableLiveData<LatLng>()
    val requestListFocusLiveData: LiveData<RecyclerViewType>
    private var isLoading = false
    private val mapItems: HashMap<LatLng, RecyclerViewType> = HashMap()

    init {
        requestListFocusLiveData = Transformations.map(itemFocused, Function {
            return@Function mapItems[it]
        })
    }

    fun fetchTaxisByLocationPage(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double) {
        GlobalScope.launch(ioDispatcher) {
            syncRepository(p1Lat,
                p1Lon,
                p2Lat,
                p2Lon)
        }
    }

    @Synchronized
    @WorkerThread
    private suspend fun syncRepository(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double) {
        if (!isLoading) {
            isLoading = true

            val response = iRepository.requestTaxisByLocation(p1Lat,
                p1Lon,
                p2Lat,
                p2Lon)
            when {
                response.status == Status.SUCCESS -> {
                    handleSuccessCase(response)
                }
                response.status == Status.LOADING -> isLoading = true

                else -> {
                    handleErrorCase(response)
                }
            }
        }
    }

    @WorkerThread
    private suspend fun handleSuccessCase(response: Resource<List<Taxi>>) {
        isLoading = false
        response.info?.let {
            addItemsAndNotify(it)
        }
    }

    @WorkerThread
    private suspend fun handleErrorCase(response: Resource<List<Taxi>>) {
        isLoading = false
        publishUIResults(errorLiveData, response.message)
    }

    @WorkerThread
    private suspend fun addItemsAndNotify(itemsToAdd: List<RecyclerViewType>) {
        itemsToAdd.forEach {
            if (it is Taxi) {
                mapItems[it.latLong] = it
            }
        }
        publishUIResults(taxiMapLiveData, mapItems)
    }

    @UiThread
    private suspend fun <T> publishUIResults(liveData: MutableLiveData<T>, data: T) {
        withContext(mainDispatcher) {
            liveData.value = data
        }
    }
}