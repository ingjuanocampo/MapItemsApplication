package com.juanocampo.mytaxy.test.di

import com.juanocampo.mytaxy.test.model.IRepository
import com.juanocampo.mytaxy.test.model.di.RepositoryModule
import dagger.Component

@ApplicationScope
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    fun provideRepository(): IRepository
}