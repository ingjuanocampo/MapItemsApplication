package com.juanocampo.mytaxy.test.viewmodel

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.IRepository
import com.juanocampo.mytaxy.test.model.domain.Status
import com.juanocampo.mytaxy.test.model.domain.Taxi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TaxiViewModel(private val iRepository: IRepository) : ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val taxiMapLiveData = MutableLiveData<HashMap<LatLng, Taxi>>()

    private val mapClickedLivedData = MutableLiveData<LatLng>()
    private val itemCLickedLiveData = MutableLiveData<Taxi>()
    private val requestListFocusLiveData: LiveData<Taxi>
    private val requestMapFocusLiveData: LiveData<LatLng>

    private var mapItems: HashMap<LatLng, Taxi> = HashMap()

    init {
        requestListFocusLiveData = Transformations.map(mapClickedLivedData, Function {
            return@Function mapItems[it]
        })

        requestMapFocusLiveData = Transformations.map(itemCLickedLiveData, Function {
            return@Function it.latLong
        })
    }

    private var disposable: Disposable? = null

    fun fetchTaxisByLocationPage(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double) {
        disposable = iRepository.requestTaxisByLocation(
            p1Lat,
            p1Lon,
            p2Lat,
            p2Lon
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
            when {
                response.status == Status.SUCCESS -> {
                    response.info?.let {
                        mapItems = response.info
                        publishUIResults(taxiMapLiveData, mapItems)
                    }
                }
                else -> {
                    handleErrorCase(response.message)
                }
            }

        }, {
            handleErrorCase(it.message ?: "Something when wrong, please try later")
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    private fun handleErrorCase(message: String) {
        publishUIResults(errorLiveData, message)
    }

    @UiThread
    private fun <T> publishUIResults(liveData: MutableLiveData<T>, data: T) {
        liveData.value = data
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