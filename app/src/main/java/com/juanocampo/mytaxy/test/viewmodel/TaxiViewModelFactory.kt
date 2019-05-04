package com.juanocampo.mytaxy.test.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.juanocampo.mytaxy.test.model.IRepository

class TaxiViewModelFactory(private val repository: IRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (TaxiViewModel::class.java.isAssignableFrom(modelClass)) return TaxiViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}