package juanocampo.myapplication.data.source.di

import dagger.Subcomponent
import juanocampo.myapplication.domain.repository.IRepository
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope

@RepositoryScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositorySubComponent {

    fun repository(): IRepository

    @Subcomponent.Builder
    interface Builder {
        fun build(): RepositorySubComponent
    }
}