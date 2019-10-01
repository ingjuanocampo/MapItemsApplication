package com.juanocampo.map.test.domain.di

import com.juanocampo.map.test.data.source.di.RepositorySubComponent
import com.juanocampo.map.test.domain.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesSyncRepositoryUseCase(builder: RepositorySubComponent.Builder) =
        SyncRepositoryUseCase(builder.build().repository())

}