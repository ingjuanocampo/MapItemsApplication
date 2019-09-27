package com.juanocampo.map.test.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.juanocampo.map.test.domain.SyncRepositoryUseCase

class TaxiViewModelFactory(private val syncRepositoryUseCase: SyncRepositoryUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (TaxiViewModel::class.java.isAssignableFrom(modelClass)) return TaxiViewModel(syncRepositoryUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}