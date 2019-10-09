package juanocampo.myapplication.data.di

import juanocampo.myapplication.data.source.di.RepositorySubComponent
import dagger.Module
import dagger.Provides
import dagger.Reusable
import juanocampo.myapplication.domain.usecase.SyncRepositoryUseCase

@Module(subcomponents = [RepositorySubComponent::class])
class DataModule {

    @Reusable
    @Provides
    fun providesSyncRepositoryUseCase(builder: RepositorySubComponent.Builder) =
        SyncRepositoryUseCase(builder.build().repository())
}