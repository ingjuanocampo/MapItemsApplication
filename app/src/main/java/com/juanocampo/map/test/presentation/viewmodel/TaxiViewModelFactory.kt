package com.juanocampo.map.test.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.juanocampo.map.test.domain.usecase.LoginUseCase
import com.juanocampo.map.test.domain.usecase.SyncRepositoryUseCase

class TaxiViewModelFactory(private val loginUseCase: LoginUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (TaxiViewModel::class.java.isAssignableFrom(modelClass)) return TaxiViewModel(loginUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}