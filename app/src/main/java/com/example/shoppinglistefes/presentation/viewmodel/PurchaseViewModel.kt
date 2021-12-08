package com.example.shoppinglistefes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistefes.domain.Purchase
import com.example.shoppinglistefes.domain.PurchaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val repository: PurchaseRepository
) : ViewModel() {
    val allPurchase: Flow<List<Purchase>> = repository.allPurchases
    val allWastedPurchase: Flow<List<Purchase>> = repository.allWastedPurchase

    fun insertPurchase(purchase: Purchase) = viewModelScope.launch {
        repository.insertPurchase(purchase)
    }

    fun deletePurchase(purchase: Purchase) = viewModelScope.launch {
        repository.deletePurchase(purchase)
    }

    fun updatePurchase(purchase: Purchase) = viewModelScope.launch {
        repository.updatePurchase(purchase)
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