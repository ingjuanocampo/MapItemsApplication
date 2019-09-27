package com.juanocampo.mytaxy.test

import android.app.Application
import com.juanocampo.mytaxy.test.di.AppComponent
import com.juanocampo.mytaxy.test.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TaxiApp: Application(), HasAndroidInjector {


    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector!!
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        var instance: TaxiApp? = null
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
    }


}