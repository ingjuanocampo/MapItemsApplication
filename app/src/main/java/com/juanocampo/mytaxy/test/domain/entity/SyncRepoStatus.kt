package com.juanocampo.mytaxy.test.domain.entity

import com.juanocampo.mytaxy.test.data.entity.Taxi

sealed class SyncRepoStatus {
    class Success(val list: List<Taxi>): SyncRepoStatus()
    class Error(val e: String): SyncRepoStatus()
}