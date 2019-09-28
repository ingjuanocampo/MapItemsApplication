package com.juanocampo.mytaxy.test.model.source.remote

import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiApiResponse
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse
import com.juanocampo.mytaxy.test.model.source.remote.service.TaxiApi
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class RemoteDataSourceTest {

    @Mock
    lateinit var taxiApi: TaxiApi

    lateinit var remoteDataSource: RemoteDataSource


    @get:Rule
    val exception: ExpectedException = ExpectedException.none()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSource(taxiApi)
    }

    @Test
    fun `fetchTaxisByLocation with an empty poiList list will return empty TaxiResponse List`() {
        var taxiResponse = TaxiApiResponse(null)
        whenever(taxiApi.fetchTaxisByLocation("0.0", "0.0", "0.0", "0.0")).thenReturn(Single.just(taxiResponse))
        val testObserver = TestObserver<List<TaxiResponse>>()

        remoteDataSource.fetchTaxisByLocation(0.0, 0.0, 0.0, 0.0).toObservable().subscribe(testObserver)

        verify(taxiApi).fetchTaxisByLocation("0.0", "0.0", "0.0", "0.0")
        testObserver.assertValueAt(0, Predicate { return@Predicate it.isNullOrEmpty() })
        testObserver.assertComplete()
    }

    @Test
    fun `fetchTaxisByLocation with an empty poiList list will return valid TaxiResponse List`() {
        val poiList = ArrayList<TaxiResponse>()
        poiList.add(TaxiResponse(0, null))
        var taxiResponse = TaxiApiResponse(poiList)

        whenever(taxiApi.fetchTaxisByLocation("0.0", "0.0", "0.0", "0.0")).thenReturn(Single.just(taxiResponse))
        val testObserver = TestObserver<List<TaxiResponse>>()

        remoteDataSource.fetchTaxisByLocation(0.0, 0.0, 0.0, 0.0).toObservable().subscribe(testObserver)

        verify(taxiApi).fetchTaxisByLocation("0.0", "0.0", "0.0", "0.0")
        testObserver.assertValueAt(0, poiList)
        testObserver.assertComplete()
    }
}