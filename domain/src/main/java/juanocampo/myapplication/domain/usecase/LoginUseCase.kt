package juanocampo.myapplication.domain.usecase

import juanocampo.myapplication.domain.entity.MapInfo
import juanocampo.myapplication.domain.entity.SyncRepoStatus

class LoginUseCase(private val syncRepositoryUseCase: SyncRepositoryUseCase) {

    suspend operator fun invoke(): SyncRepoStatus {
        //do login
        //fetch map information
        //after login finally sync info
        return syncRepositoryUseCase(
            MapInfo.HAMBURG_BOUND.first.latitude, MapInfo.HAMBURG_BOUND.first.longitude,
            MapInfo.HAMBURG_BOUND.second.latitude, MapInfo.HAMBURG_BOUND.second.longitude)
    }
}