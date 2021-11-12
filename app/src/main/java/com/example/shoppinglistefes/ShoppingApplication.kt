package com.example.shoppinglistefes

import android.app.Application
import com.example.shoppinglistefes.data.PurchaseDatabase

class ShoppingApplication : Application() {
    val database by lazy { PurchaseDatabase.getDatabase(this) }
}