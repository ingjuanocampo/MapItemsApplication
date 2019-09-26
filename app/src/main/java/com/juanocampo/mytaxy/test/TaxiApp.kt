package com.juanocampo.mytaxy.test

import android.app.Application
import com.juanocampo.mytaxy.test.di.AppComponent
import com.juanocampo.mytaxy.test.di.DaggerAppComponent
import dagger.android.HasActivityInjector
import android.app.Activity
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class TaxiApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector!!
    }

}