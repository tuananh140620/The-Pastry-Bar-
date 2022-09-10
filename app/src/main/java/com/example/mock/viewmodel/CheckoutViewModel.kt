package com.example.mock.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mock.entity.Order
import com.example.mock.repository.CheckoutRepository

class CheckoutViewModel (application: Application) : ViewModel(){

    private val CheckoutRepository: CheckoutRepository = CheckoutRepository(application)

    class FactoryViewModel(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CheckoutRepository::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CheckoutRepository(application) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }
    }

    fun TheHistoryOrder(checkoutList:ArrayList<Order>, resId : Int, type:String, resName:String, totalPrice:Float, uid:String ) =
    CheckoutRepository.createTheHistoryOrder(checkoutList, resId ,type,resName,totalPrice,uid )


}