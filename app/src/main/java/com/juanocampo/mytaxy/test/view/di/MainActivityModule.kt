package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.view.MapsActivity
import com.juanocampo.mytaxy.test.view.fragment.HamburgMapFragment
import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @MainScreenScope
    @ContributesAndroidInjector(modules = [PresentationMainModule::class])
    abstract fun mapsAcivity(): MapsActivity

    @MainScreenScope
    @ContributesAndroidInjector(modules = [PresentationMainModule::class])
    abstract fun listFragment(): TaxisListFragment

    @MainScreenScope
    @ContributesAndroidInjector(modules = [PresentationMainModule::class])
    abstract fun mapFragment(): HamburgMapFragment

}