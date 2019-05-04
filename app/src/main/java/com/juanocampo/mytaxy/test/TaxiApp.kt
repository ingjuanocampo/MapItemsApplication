package com.juanocampo.mytaxy.test

import android.app.Application
import com.juanocampo.mytaxy.test.di.AppComponent
import com.juanocampo.mytaxy.test.di.DaggerAppComponent

class TaxiApp: Application() {

    companion object {
        var instance: TaxiApp? = null
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().build()

    }

}