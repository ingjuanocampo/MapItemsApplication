package com.juanocampo.map.test.presentation.di

import com.juanocampo.map.test.presentation.viewmodel.TaxiViewModelFactory
import com.juanocampo.map.test.view.di.MainScreenScope
import dagger.Module
import dagger.Provides
import juanocampo.myapplication.domain.usecase.LoadHamburgTaxisUseCase

@Module
class PresentationMainModule {

    @Provides
    @MainScreenScope
    fun providesViewModelFactory(loginUseCase: LoadHamburgTaxisUseCase) =
        TaxiViewModelFactory(loginUseCase)

}