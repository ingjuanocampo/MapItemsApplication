package com.juanocampo.map.test.view.di

import com.juanocampo.map.test.view.fragment.HamburgMapFragment
import com.juanocampo.map.test.view.fragment.TaxisListFragment
import dagger.Subcomponent

@MainScreenScope
@Subcomponent(modules = [PresentationMainModule::class])
interface MainScreenComponent {

    fun inject(fragment: TaxisListFragment)

    fun inject(fragment: HamburgMapFragment)

}