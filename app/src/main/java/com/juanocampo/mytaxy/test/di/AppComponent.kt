package com.juanocampo.mytaxy.test.di

import android.app.Application
import com.juanocampo.mytaxy.test.TaxiApp
import com.juanocampo.mytaxy.test.view.di.MainScreenComponent
import com.juanocampo.mytaxy.test.view.di.PresentationMainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
    DataModule::class,
    DomainModule::class])
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(taxiApp: TaxiApp)

}