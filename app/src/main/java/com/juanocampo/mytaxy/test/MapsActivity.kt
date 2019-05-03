package com.juanocampo.mytaxy.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLngBounds



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        val HAMBURG = LatLng(53.5, 10.02)
        val HAMBURG_BOUND = LatLngBounds(LatLng(53.445324, 9.585010), LatLng(53.703293, 10.433850))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap.setMinZoomPreference(10.0f)
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HAMBURG))
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG_BOUND.center, 10f))
        mMap.setLatLngBoundsForCameraTarget(HAMBURG_BOUND)


    }

}
