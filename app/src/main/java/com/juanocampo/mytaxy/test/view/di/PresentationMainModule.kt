package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.model.di.RepositorySubComponent
import com.juanocampo.mytaxy.test.viewmodel.TaxiViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationMainModule {

    @Provides
    @MainScreenScope
    fun providesViewModelFactory(builder: RepositorySubComponent.Builder) = TaxiViewModelFactory(builder.build().repository())

}