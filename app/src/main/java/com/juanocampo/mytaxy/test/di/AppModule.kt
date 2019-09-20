package com.juanocampo.mytaxy.test.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun providesApplicationContext(applicationContext: Application): Context

}