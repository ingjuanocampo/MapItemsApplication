package com.juanocampo.mytaxy.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MapsActivity : AppCompatActivity() {

    // https://fake-poi-api.mytaxi.com/?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
    }


}
