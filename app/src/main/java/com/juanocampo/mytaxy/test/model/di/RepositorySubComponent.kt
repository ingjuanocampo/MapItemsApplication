package com.juanocampo.mytaxy.test.model.di

import com.juanocampo.mytaxy.test.model.IRepository
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope

@RepositoryScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositorySubComponent {

    fun providesRepository(): IRepository

    @Subcomponent.Builder
    interface Builder {
        fun requestModule(module: RepositoryModule): Builder
        fun build(): RepositorySubComponent
    }
}