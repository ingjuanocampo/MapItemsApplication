package com.juanocampo.map.test.presentation.viewmodel

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import com.google.android.gms.maps.model.LatLng
import com.juanocampo.map.test.presentation.entitity.TaxiViewType
import com.juanocampo.map.test.utils.delegate.model.RecyclerViewType
import juanocampo.myapplication.domain.entity.SyncRepoStatus
import juanocampo.myapplication.domain.entity.Taxi
import juanocampo.myapplication.domain.usecase.LoginUseCase
import kotlinx.coroutines.*

class TaxiViewModel(private val loginUseCase: LoginUseCase,
                    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val taxiMapLiveData = MutableLiveData<HashMap<LatLng, RecyclerViewType>>()

    private val mapClickedLivedData = MutableLiveData<LatLng>()
    private val itemCLickedLiveData =  MutableLiveData<TaxiViewType>()
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

    fun fetchTaxisByLocationPage() {
        GlobalScope.launch(ioDispatcher) {

            when(val syncStatus = loginUseCase()) {
                is SyncRepoStatus.Success -> {
                    val uiItems = mapToUiItems(syncStatus.list)
                    handleSuccessCase(uiItems)
                }
                is SyncRepoStatus.Error -> handleErrorCase(syncStatus.e)
            }

        }
    }

    private suspend fun mapToUiItems(list: List<Taxi>): List<TaxiViewType> {
        return withContext(ioDispatcher) {
            return@withContext list.map { TaxiViewType(it.id, LatLng(it.latLong.latitude, it.latLong.longitude)) }
        }
    }

    @WorkerThread
    private suspend fun handleSuccessCase(response: List<TaxiViewType>) {
        addItemsAndNotify(response)
    }

    @WorkerThread
    private suspend fun handleErrorCase(e: String) {
        publishUIResults(errorLiveData, e)
    }

    @WorkerThread
    private suspend fun addItemsAndNotify(itemsToAdd: List<RecyclerViewType>) {
        itemsToAdd.forEach {
            if (it is TaxiViewType) {
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
    fun setClickedItem(taxi: TaxiViewType) {
        itemCLickedLiveData.value = taxi
    }

    fun getRequestListObserver() = requestListFocusLiveData

    fun getRequestMapObserver() = requestMapFocusLiveData
}