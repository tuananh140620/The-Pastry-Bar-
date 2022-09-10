package com.example.mock.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mock.repository.MainRepository

class MainViewModel(application: Application): ViewModel() {
    private val mainRepository = MainRepository(application)
    class FactoryViewModel(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }
    }
    fun getProductFromFireStore() = mainRepository.getProductFromFireStore()
}