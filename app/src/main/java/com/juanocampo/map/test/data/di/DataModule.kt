package com.juanocampo.map.test.data.di

import com.juanocampo.map.test.data.source.di.RepositorySubComponent
import com.juanocampo.map.test.domain.usecase.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module(subcomponents = [RepositorySubComponent::class])
class DataModule {

    @Provides
    fun providesSyncRepositoryUseCase(builder: RepositorySubComponent.Builder) =
        SyncRepositoryUseCase(builder.build().repository())
}