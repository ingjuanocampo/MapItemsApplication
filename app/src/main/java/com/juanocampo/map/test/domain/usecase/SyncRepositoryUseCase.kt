package com.juanocampo.map.test.domain.usecase

import android.support.annotation.WorkerThread
import com.juanocampo.map.test.domain.repository.IRepository
import com.juanocampo.map.test.domain.entity.SyncRepoStatus

class SyncRepositoryUseCase(private val iRepository: IRepository) {

    @Synchronized
    @WorkerThread
    suspend operator fun invoke(
        p1Lat: Double,
        p1Lon: Double,
        p2Lat: Double,
        p2Lon: Double
    ): SyncRepoStatus {
        return try {
            val response = iRepository.requestTaxisByLocation(
                p1Lat,
                p1Lon,
                p2Lat,
                p2Lon
            )
            if (response.isNotEmpty()) {
                SyncRepoStatus.Success(response)
            } else {
                SyncRepoStatus.Error("Not results")
            }
        } catch (e: Exception) {
            SyncRepoStatus.Error(e.message ?: "Error occurred")
        }
    }
}