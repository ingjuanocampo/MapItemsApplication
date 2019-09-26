package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@MainScreenScope
@Subcomponent(modules = [PresentationMainModule::class])
interface MainScreenComponent: AndroidInjector<TaxisListFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<TaxisListFragment>()

}