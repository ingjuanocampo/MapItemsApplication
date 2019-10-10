package juanocampo.myapplication.data.source.di

import juanocampo.myapplication.data.Repository
import juanocampo.myapplication.data.source.remote.IRemoteDataSource
import juanocampo.myapplication.data.source.remote.RemoteDataSource
import juanocampo.myapplication.data.source.remote.mapper.TaxiMapper
import juanocampo.myapplication.data.source.remote.service.TaxiApi
import dagger.Module
import dagger.Provides
import juanocampo.myapplication.domain.repository.IRepository
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