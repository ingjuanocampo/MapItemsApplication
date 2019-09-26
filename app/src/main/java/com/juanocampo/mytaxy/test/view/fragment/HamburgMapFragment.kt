package com.juanocampo.mytaxy.test.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.juanocampo.mytaxy.test.R
import com.juanocampo.mytaxy.test.di.AndroidInjectorUtils
import com.juanocampo.mytaxy.test.data.entity.Taxi
import com.juanocampo.mytaxy.test.utils.delegate.model.RecyclerViewType
import com.juanocampo.mytaxy.test.presentation.viewmodel.TaxiViewModel
import com.juanocampo.mytaxy.test.presentation.viewmodel.TaxiViewModelFactory
import javax.inject.Inject

class HamburgMapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        val HAMBURG = LatLng(53.5, 10.02)
        val HAMBURG_BOUND = LatLngBounds(LatLng(53.445324, 9.585010), LatLng(53.703293, 10.433850))
    }
    //@Inject
    //lateinit var viewModelFactory: TaxiViewModelFactory

    private lateinit var viewModel: TaxiViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
       /* viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TaxiViewModel::class.java)
        viewModel.fetchTaxisByLocationPage(HAMBURG_BOUND.southwest.latitude, HAMBURG_BOUND.southwest.longitude,
            HAMBURG_BOUND.northeast.latitude, HAMBURG_BOUND.northeast.longitude)*/
    }

    private fun addMarkerToMap(taxis: ArrayList<RecyclerViewType>) {
        mMap.clear()
        taxis.forEach {
            if (it is Taxi) {
                val markerOptions = MarkerOptions()
                markerOptions.position(it.latLong)
                markerOptions.title("Cab id: ${it.id}")
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_local_taxi_black_18dp))
                mMap.addMarker(markerOptions)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HAMBURG))
        mMap.setLatLngBoundsForCameraTarget(HAMBURG_BOUND)

        mMap.setOnMarkerClickListener {
            animateCamera(it.position)
            it.showInfoWindow()
            return@setOnMarkerClickListener true
        }

        viewModel.taxiMapLiveData.observe(this, Observer {
            it?.let { taxis ->
                addMarkerToMap(ArrayList(taxis.values))
            }
        })

        viewModel.getRequestMapObserver().observe(this, Observer {
            animateCamera(it)
        })
    }

    private fun animateCamera(position: LatLng?) {
        position?.let {
            viewModel.setClickedMarker(it)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 13f))
        }
    }
}