package com.juanocampo.mytaxy.test.data.di

import com.juanocampo.mytaxy.test.domain.IRepository
import dagger.Subcomponent
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