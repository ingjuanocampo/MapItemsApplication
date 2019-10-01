package com.juanocampo.map.test.data.source.di

import com.juanocampo.map.test.domain.repository.IRepository
import com.juanocampo.map.test.data.Repository
import com.juanocampo.map.test.data.source.remote.IRemoteDataSource
import com.juanocampo.map.test.data.source.remote.RemoteDataSource
import com.juanocampo.map.test.data.source.remote.mapper.TaxiMapper
import com.juanocampo.map.test.data.source.remote.service.TaxiApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @Provides
    fun providesApi(): TaxiApi {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://fake-poi-api.mytaxi.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        return retrofit.create(TaxiApi::class.java)
    }

    @Provides
    fun providesRemoteDataSource(taxiApi: TaxiApi): IRemoteDataSource =
        RemoteDataSource(api = taxiApi)

    @Provides
    fun providesMapper() = TaxiMapper()

    @RepositoryScope
    @Provides
    fun providesRepository(iRemoteDataSource: IRemoteDataSource, mapper: TaxiMapper): IRepository =
        Repository(iRemoteDataSource, mapper)

}