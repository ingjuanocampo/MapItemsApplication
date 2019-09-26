package com.juanocampo.mytaxy.test.view.di

import com.juanocampo.mytaxy.test.view.MapsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@MainScreenScope
@Subcomponent(modules = [PresentationMainModule::class])
interface MapActivityComponent: AndroidInjector<MapsActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MapsActivity>()
}