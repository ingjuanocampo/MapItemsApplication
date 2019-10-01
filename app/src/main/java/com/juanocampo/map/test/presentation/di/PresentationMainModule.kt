package com.juanocampo.map.test.presentation.di

import com.juanocampo.map.test.domain.usecase.LoginUseCase
import com.juanocampo.map.test.presentation.viewmodel.TaxiViewModelFactory
import com.juanocampo.map.test.view.di.MainScreenScope
import dagger.Module
import dagger.Provides

@Module
class PresentationMainModule {

    @Provides
    @MainScreenScope
    fun providesViewModelFactory(loginUseCase: LoginUseCase) =
        TaxiViewModelFactory(loginUseCase)

}