package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.domain.SyncRepositoryUseCase
import com.juanocampo.mytaxy.test.presentation.viewmodel.TaxiViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationMainModule {

    @Provides
    @MainScreenScope
    fun providesViewModelFactory(syncRepositoryUseCase: SyncRepositoryUseCase) = TaxiViewModelFactory(syncRepositoryUseCase)

}