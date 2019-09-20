package com.juanocampo.mytaxy.test.di

import android.app.Application
import com.juanocampo.mytaxy.test.model.di.RepositorySubComponent
import com.juanocampo.mytaxy.test.view.di.ViewComponent
import com.juanocampo.mytaxy.test.view.di.ViewModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun domain(): RepositorySubComponent.Builder

    fun view(): ViewComponent.Builder

}