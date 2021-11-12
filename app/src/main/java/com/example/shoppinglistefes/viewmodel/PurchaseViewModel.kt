package com.example.shoppinglistefes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistefes.data.Purchase
import com.example.shoppinglistefes.data.PurchaseRepository
import java.lang.IllegalArgumentException
import kotlinx.coroutines.launch

class PurchaseViewModel(
    private val repository: PurchaseRepository
) : ViewModel() {
    val allPurchase: LiveData<List<Purchase>> = repository.allPurchases.asLiveData()

    fun insertPurchase(purchase: Purchase) = viewModelScope.launch {
        repository.insertPurchase(purchase)
    }

    fun deletePurchase(purchase: Purchase) = viewModelScope.launch {
        repository.deletePurchase(purchase)
    }
}

class PurchaseViewModelFactory(
    private val repository: PurchaseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PurchaseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}