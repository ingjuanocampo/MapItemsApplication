package com.juanocampo.mytaxy.test.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.juanocampo.mytaxy.test.domain.IRepository
import com.juanocampo.mytaxy.test.domain.SyncRepositoryUseCase

class TaxiViewModelFactory(private val syncRepositoryUseCase: SyncRepositoryUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (TaxiViewModel::class.java.isAssignableFrom(modelClass)) return TaxiViewModel(syncRepositoryUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}