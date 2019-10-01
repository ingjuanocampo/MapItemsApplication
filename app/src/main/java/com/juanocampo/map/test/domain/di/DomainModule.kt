package com.juanocampo.map.test.domain.di

import com.juanocampo.map.test.domain.usecase.LoginUseCase
import com.juanocampo.map.test.domain.usecase.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesLoginUseCase(syncRepositoryUseCase: SyncRepositoryUseCase) =
        LoginUseCase(syncRepositoryUseCase)
}