package juanocampo.myapplication.domain.entity

sealed class SyncRepoStatus {
    class Success(val list: List<Taxi>): SyncRepoStatus()
    class Error(val e: String): SyncRepoStatus()
}