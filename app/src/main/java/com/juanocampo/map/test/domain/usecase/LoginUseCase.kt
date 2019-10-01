package com.juanocampo.map.test.domain.usecase

import com.juanocampo.map.test.domain.entity.MapInfo
import com.juanocampo.map.test.domain.entity.SyncRepoStatus

class LoginUseCase(private val syncRepositoryUseCase: SyncRepositoryUseCase) {

    suspend operator fun invoke(): SyncRepoStatus {
        //do login
        //fetch map information
        //after login finally sync info
        return syncRepositoryUseCase(MapInfo.HAMBURG_BOUND.southwest.latitude, MapInfo.HAMBURG_BOUND.southwest.longitude,
            MapInfo.HAMBURG_BOUND.northeast.latitude, MapInfo.HAMBURG_BOUND.northeast.longitude)
    }
}