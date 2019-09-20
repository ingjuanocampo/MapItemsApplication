package com.juanocampo.mytaxy.test.model.di

import com.juanocampo.mytaxy.test.model.IRepository
import com.juanocampo.mytaxy.test.model.Repository
import com.juanocampo.mytaxy.test.model.source.remote.IRemoteDataSource
import com.juanocampo.mytaxy.test.model.source.remote.RemoteDataSource
import com.juanocampo.mytaxy.test.model.source.remote.mapper.TaxiMapper
import com.juanocampo.mytaxy.test.model.source.remote.service.TaxiApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    fun providesApi(): TaxiApi {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://fake-poi-api.mytaxi.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        return retrofit.create(TaxiApi::class.java)
    }

    @RepositoryScope
    @Provides
    fun providesRemoteDataSource(taxiApi: TaxiApi): IRemoteDataSource = RemoteDataSource(api = taxiApi)

    @RepositoryScope
    @Provides
    fun providesMapper() = TaxiMapper()

    @Provides
    fun providesRepository(iRemoteDataSource: IRemoteDataSource, mapper: TaxiMapper): IRepository =
        Repository(iRemoteDataSource, mapper)

}