package juanocampo.myapplication.presentation.di

import juanocampo.myapplication.presentation.viewmodel.TaxiViewModelFactory
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