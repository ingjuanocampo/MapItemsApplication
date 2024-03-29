package com.juanocampo.map.test.view.di

import com.juanocampo.map.test.view.MapsActivity
import com.juanocampo.map.test.view.fragment.HamburgMapFragment
import com.juanocampo.map.test.view.fragment.TaxisListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import juanocampo.myapplication.presentation.di.MainScreenScope
import juanocampo.myapplication.presentation.di.PresentationMainModule

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