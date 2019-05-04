package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.model.IRepository
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    @ViewScope
    fun providesViewModelFactory(iRepository: IRepository) = TaxiViewModelFactory(iRepository)

}