package com.juanocampo.map.test

import android.app.Application
import com.juanocampo.map.test.di.AppComponent
import com.juanocampo.map.test.di.DaggerAppComponent

class TaxiApp: Application() {

    companion object {
        var instance: TaxiApp? = null
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().application(this).build()
    }

}