package com.juanocampo.mytaxy.test.view.fragment

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class HamburgMapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        val HAMBURG = LatLng(53.5, 10.02)
        val HAMBURG_BOUND = LatLngBounds(LatLng(53.445324, 9.585010), LatLng(53.703293, 10.433850))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(10.0f)
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HAMBURG))
        mMap.setLatLngBoundsForCameraTarget(HAMBURG_BOUND)
    }
}