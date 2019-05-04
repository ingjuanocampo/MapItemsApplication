package com.juanocampo.mytaxy.test.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.juanocampo.mytaxy.test.di.AndroidInjectorUtils
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModel
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModelFactory
import javax.inject.Inject

class HamburgMapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        val HAMBURG = LatLng(53.5, 10.02)
        val HAMBURG_BOUND = LatLngBounds(LatLng(53.445324, 9.585010), LatLng(53.703293, 10.433850))
    }
    @Inject
    lateinit var viewModelFactory: TaxiViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
        AndroidInjectorUtils.inject(this)
        var viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TaxiViewModel::class.java)
        viewModel.fetchTaxisByLocationPage(HAMBURG_BOUND.southwest.latitude, HAMBURG_BOUND.southwest.longitude,
            HAMBURG_BOUND.northeast.latitude, HAMBURG_BOUND.northeast.longitude)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(10.0f)
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HAMBURG))
        mMap.setLatLngBoundsForCameraTarget(HAMBURG_BOUND)
    }
}