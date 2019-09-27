package com.juanocampo.map.test.di

import com.juanocampo.map.test.data.di.RepositorySubComponent
import dagger.Module

@Module(subcomponents = [RepositorySubComponent::class])
class DataModule