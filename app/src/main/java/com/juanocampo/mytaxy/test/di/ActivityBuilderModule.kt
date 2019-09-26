package com.juanocampo.mytaxy.test.di

import android.support.v4.app.Fragment
import com.juanocampo.mytaxy.test.view.MapsActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.juanocampo.mytaxy.test.view.di.MainScreenComponent
import com.juanocampo.mytaxy.test.view.di.MapActivityComponent
import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey


@Module
abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ClassKey(MapsActivity::class)
    abstract fun bindMapsnActivity(builder: MapActivityComponent.Builder): AndroidInjector.Factory<out MapsActivity>

    @Binds
    @IntoMap
    @ClassKey(TaxisListFragment::class)
    abstract fun bindMainActivity(builder: MainScreenComponent.Builder): AndroidInjector.Factory<out TaxisListFragment>

}