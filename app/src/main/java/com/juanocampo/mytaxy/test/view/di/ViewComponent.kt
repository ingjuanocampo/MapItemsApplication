package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.view.fragment.HamburgMapFragment
import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = [ViewModule::class])
interface ViewComponent {

    fun inject(fragment: TaxisListFragment)

    fun inject(fragment: HamburgMapFragment)

}