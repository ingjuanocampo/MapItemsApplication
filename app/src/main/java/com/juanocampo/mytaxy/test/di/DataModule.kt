package com.juanocampo.mytaxy.test.di

import com.juanocampo.mytaxy.test.model.di.RepositorySubComponent
import dagger.Module

@Module(subcomponents = [RepositorySubComponent::class])
class DataModule