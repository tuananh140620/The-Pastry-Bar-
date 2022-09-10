package com.example.mock.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mock.repository.HomeRepository

class HomeViewModel(application: Application): ViewModel() {
    var isSigningIn:Boolean = false
    private val homeRepository = HomeRepository(application)
    class FactoryViewModel(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(application) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }
    }
    fun getProductFromFireStore() = homeRepository.getProductFromFireStore()


}