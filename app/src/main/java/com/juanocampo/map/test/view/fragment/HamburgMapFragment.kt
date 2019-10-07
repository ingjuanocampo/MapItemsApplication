package com.juanocampo.map.test.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import com.juanocampo.map.test.R
import com.juanocampo.map.test.presentation.entitity.TaxiViewType
import com.juanocampo.map.test.utils.delegate.model.RecyclerViewType
import com.juanocampo.map.test.presentation.viewmodel.TaxiViewModel
import com.juanocampo.map.test.presentation.viewmodel.TaxiViewModelFactory
import dagger.android.support.AndroidSupportInjection
import juanocampo.myapplication.domain.entity.MapInfo
import javax.inject.Inject

class HamburgMapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: TaxiViewModelFactory

    private lateinit var viewModel: TaxiViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TaxiViewModel::class.java)
        viewModel.fetchTaxisByLocationPage()
    }

    private fun addMarkerToMap(taxis: ArrayList<RecyclerViewType>) {
        mMap.clear()
        taxis.forEach {
            if (it is TaxiViewType) {
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(MapInfo.HAMBURG.latitude, MapInfo.HAMBURG.longitude)))
        mMap.setLatLngBoundsForCameraTarget(LatLngBounds(LatLng(MapInfo.HAMBURG_BOUND.first.latitude, MapInfo.HAMBURG_BOUND.first.longitude),
            LatLng(MapInfo.HAMBURG_BOUND.second.latitude, MapInfo.HAMBURG_BOUND.second.longitude)))

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