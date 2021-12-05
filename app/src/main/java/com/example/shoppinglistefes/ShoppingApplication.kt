package com.example.shoppinglistefes

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.shoppinglistefes.data.PurchaseDatabase
import com.example.shoppinglistefes.data.PurchaseRepository

class ShoppingApplication : Application() {
    val database by lazy { PurchaseDatabase.getDatabase(this) }
    val repository by lazy { PurchaseRepository(database.purchaseDao()) }
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        if(sharedPreferences.getBoolean("switch_pref_theme_key",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}