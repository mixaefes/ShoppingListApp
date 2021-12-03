package com.example.shoppinglistefes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistefes.data.Purchase

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Purchase?>()
    fun select(purchase: Purchase?) {
        selected.value = purchase
    }
}