package com.juanocampo.map.test.data.di

import com.juanocampo.map.test.data.source.di.RepositorySubComponent
import dagger.Module

@Module(subcomponents = [RepositorySubComponent::class])
class DataModule