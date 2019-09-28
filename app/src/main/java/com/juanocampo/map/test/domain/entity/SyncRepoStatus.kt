package com.juanocampo.map.test.domain.entity

import com.juanocampo.map.test.data.entity.Taxi

sealed class SyncRepoStatus {
    class Success(val list: List<Taxi>): SyncRepoStatus()
    class Error(val e: String): SyncRepoStatus()
}