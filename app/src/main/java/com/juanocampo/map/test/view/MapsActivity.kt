package com.juanocampo.map.test.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.juanocampo.map.test.R
import com.juanocampo.mytaxy.test.di.AndroidInjectorUtils

class MapsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjectorUtils.buildViewComponent()
        setContentView(R.layout.activity_maps)
    }
}
