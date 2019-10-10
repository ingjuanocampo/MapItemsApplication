package juanocampo.myapplication.domain.di

import juanocampo.myapplication.domain.usecase.LoadHamburgTaxisUseCase
import juanocampo.myapplication.domain.usecase.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesLoginUseCase(syncRepositoryUseCase: SyncRepositoryUseCase) =
        LoadHamburgTaxisUseCase(syncRepositoryUseCase)
}