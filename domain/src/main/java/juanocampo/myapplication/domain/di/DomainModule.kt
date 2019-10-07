package juanocampo.myapplication.domain.di

import juanocampo.myapplication.domain.usecase.LoginUseCase
import juanocampo.myapplication.domain.usecase.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesLoginUseCase(syncRepositoryUseCase: SyncRepositoryUseCase) =
        LoginUseCase(syncRepositoryUseCase)
}