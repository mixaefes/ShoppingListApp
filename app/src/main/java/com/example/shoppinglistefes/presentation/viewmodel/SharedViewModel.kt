package com.example.shoppinglistefes.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistefes.domain.Purchase
import dagger.hilt.android.lifecycle.HiltViewModel

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Purchase?>()
    fun select(purchase: Purchase?) {
        selected.value = purchase
    }
}