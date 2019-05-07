package com.juanocampo.mytaxy.test.model

import com.google.android.gms.maps.model.LatLng
import com.juanocampo.mytaxy.test.model.domain.Resource
import com.juanocampo.mytaxy.test.model.domain.Status
import com.juanocampo.mytaxy.test.model.domain.Taxi
import com.juanocampo.mytaxy.test.model.source.remote.IRemoteDataSource
import com.juanocampo.mytaxy.test.model.source.remote.domain.Coordinate
import com.juanocampo.mytaxy.test.model.source.remote.domain.TaxiResponse
import com.juanocampo.mytaxy.test.model.source.remote.mapper.TaxiMapper
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @Mock
    lateinit var iRemoteDataSource: IRemoteDataSource

    lateinit var taxiResponseMapper: TaxiMapper

    lateinit var repository: Repository

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        taxiResponseMapper = TaxiMapper()
        repository = Repository(iRemoteDataSource, taxiResponseMapper)
    }

    @Test
    fun `requestTaxisByLocation with an empty list will return a resource error`() {
        Mockito.`when`(iRemoteDataSource.fetchTaxisByLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(Single.just(emptyList()))
        val testObserver = TestObserver<Resource<HashMap<LatLng, Taxi>>>()
        repository.requestTaxisByLocation(0.0, 0.0, 0.0, 0.0).subscribe(testObserver)
        testObserver.assertValueAt(0, Predicate { return@Predicate it.status == Status.ERROR })
        testObserver.assertValueAt(0, Predicate { return@Predicate it.message == "could not load info, try later" })
    }


    @Test
    fun `requestTaxisByLocation with a valid response list will map a success`() {
        val taxiResponseList = ArrayList<TaxiResponse>()
        val taxiItem = TaxiResponse(1, Coordinate(0.0,0.0))
        taxiResponseList.add(taxiItem)
        Mockito.`when`(iRemoteDataSource.fetchTaxisByLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(Single.just(taxiResponseList))
        val testObserver = TestObserver<Resource<HashMap<LatLng, Taxi>>>()
        repository.requestTaxisByLocation(0.0, 0.0, 0.0, 0.0).subscribe(testObserver)
        testObserver.assertValueAt(0, Predicate { return@Predicate it.status == Status.SUCCESS })
        testObserver.assertValueAt(0, Predicate { return@Predicate it.info?.size == 1 })
        testObserver.assertValueAt(0, Predicate { return@Predicate ArrayList(it.info?.entries)[0].key.longitude == taxiItem.coordinate?.longitude})
        testObserver.assertValueAt(0, Predicate { return@Predicate ArrayList(it.info?.entries)[0].key.latitude == taxiItem.coordinate?.latitude})

        testObserver.assertValueAt(0, Predicate { return@Predicate ArrayList(it.info?.values)[0].id == taxiItem.id})
        testObserver.assertValueAt(0, Predicate { return@Predicate ArrayList(it.info?.values)[0].latLong.latitude == taxiItem?.coordinate?.latitude})
        testObserver.assertValueAt(0, Predicate { return@Predicate ArrayList(it.info?.values)[0].latLong.longitude == taxiItem.coordinate?.longitude})
    }
}