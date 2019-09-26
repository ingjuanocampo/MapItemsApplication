package com.juanocampo.mytaxy.test.di

import com.juanocampo.mytaxy.test.data.di.RepositorySubComponent
import com.juanocampo.mytaxy.test.domain.SyncRepositoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesSyncRepositoryUseCase(builder: RepositorySubComponent.Builder) =
        SyncRepositoryUseCase(builder.build().repository())

}