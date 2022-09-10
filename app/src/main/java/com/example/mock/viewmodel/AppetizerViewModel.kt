package com.example.mock.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mock.entity.Food
import com.example.mock.repository.AppetizerRepository
import com.example.mock.repository.FireStoreRepository
import kotlinx.coroutines.launch

class AppetizerViewModel(application: Application) : ViewModel() {
    private val fireStoreRepository = AppetizerRepository(application)

    class FactoryViewModel(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppetizerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppetizerViewModel(application) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }
    }
    fun getMenuAppetizer() = viewModelScope.launch() {
        val list = fireStoreRepository.getMenuAppetizer()
        list.forEach { food: Food ->  }
    }
}