package com.juanocampo.map.test.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.juanocampo.map.test.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MapsActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector!!
    }
}