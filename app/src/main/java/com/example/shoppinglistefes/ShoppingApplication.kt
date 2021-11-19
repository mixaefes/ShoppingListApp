package com.example.shoppinglistefes

import android.app.Application
import com.example.shoppinglistefes.data.PurchaseDatabase
import com.example.shoppinglistefes.data.PurchaseRepository

class ShoppingApplication : Application() {
    val database by lazy { PurchaseDatabase.getDatabase(this) }
    val repository by lazy { PurchaseRepository(database.purchaseDao()) }
}