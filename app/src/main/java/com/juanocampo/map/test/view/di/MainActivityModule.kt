package com.juanocampo.map.test.view.di

import com.juanocampo.map.test.presentation.di.PresentationMainModule
import com.juanocampo.map.test.view.MapsActivity
import com.juanocampo.map.test.view.fragment.HamburgMapFragment
import com.juanocampo.map.test.view.fragment.TaxisListFragment
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