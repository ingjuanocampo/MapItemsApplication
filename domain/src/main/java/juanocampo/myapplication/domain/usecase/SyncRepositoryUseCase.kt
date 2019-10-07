package juanocampo.myapplication.domain.usecase

import juanocampo.myapplication.domain.repository.IRepository
import juanocampo.myapplication.domain.entity.SyncRepoStatus

class SyncRepositoryUseCase(private val iRepository: IRepository) {

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