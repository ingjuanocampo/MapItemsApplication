package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.di.AppComponent
import com.juanocampo.mytaxy.test.view.fragment.HamburgMapFragment
import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment
import dagger.Component

@ViewScope
@Component(dependencies = [AppComponent::class], modules = [ViewModule::class])
interface ViewComponent {

    fun inject(fragment: TaxisListFragment)

    fun inject(fragment: HamburgMapFragment)

}