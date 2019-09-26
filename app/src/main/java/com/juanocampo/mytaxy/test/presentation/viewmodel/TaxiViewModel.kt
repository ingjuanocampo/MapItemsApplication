package com.juanocampo.mytaxy.test.presentation.viewmodel

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.data.entity.Taxi
import com.juanocampo.mytaxy.test.domain.SyncRepositoryUseCase
import com.juanocampo.mytaxy.test.domain.entity.SyncRepoStatus
import com.juanocampo.mytaxy.test.utils.delegate.model.RecyclerViewType
import kotlinx.coroutines.*

class TaxiViewModel(private val syncRepositoryUseCase: SyncRepositoryUseCase,
                    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val taxiMapLiveData = MutableLiveData<HashMap<LatLng, RecyclerViewType>>()

    private val mapClickedLivedData = MutableLiveData<LatLng>()
    private val itemCLickedLiveData =  MutableLiveData<Taxi>()
    private val requestListFocusLiveData: LiveData<RecyclerViewType>
    private val requestMapFocusLiveData: LiveData<LatLng>

    private val mapItems: HashMap<LatLng, RecyclerViewType> = HashMap()

    init {
        requestListFocusLiveData = Transformations.map(mapClickedLivedData, Function {
            return@Function mapItems[it]
        })

        requestMapFocusLiveData = Transformations.map(itemCLickedLiveData, Function {
            return@Function it.latLong
        })
    }

    fun fetchTaxisByLocationPage(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double) {
        GlobalScope.launch(ioDispatcher) {
            val syncStatus = syncRepositoryUseCase(p1Lat,
                p1Lon,
                p2Lat,
                p2Lon)
            when(syncStatus) {
                is SyncRepoStatus.Success -> handleSuccessCase(syncStatus.list)
                is SyncRepoStatus.Error -> handleErrorCase(syncStatus.e)


            }

        }
    }

    @WorkerThread
    private suspend fun handleSuccessCase(response: List<Taxi>) {
        addItemsAndNotify(response)
    }

    @WorkerThread
    private suspend fun handleErrorCase(e: String) {
        publishUIResults(errorLiveData, e)
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

    @UiThread
    fun setClickedMarker(latLng: LatLng) {
        mapClickedLivedData.value = latLng
    }
    @UiThread
    fun setClickedItem(taxi: Taxi) {
        itemCLickedLiveData.value = taxi
    }

    fun getRequestListObserver() = requestListFocusLiveData

    fun getRequestMapObserver() = requestMapFocusLiveData
}