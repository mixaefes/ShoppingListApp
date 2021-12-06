package com.example.shoppinglistefes.domain

import androidx.annotation.WorkerThread
import com.example.shoppinglistefes.domain.Dao
import com.example.shoppinglistefes.domain.Purchase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PurchaseRepository @Inject constructor(private val purchaseDao: Dao) {

    val allPurchases: Flow<List<Purchase>> = purchaseDao.getAll()

    val allWastedPurchase : Flow<List<Purchase>> = purchaseDao.getAllWasted()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPurchase(purchase: Purchase) {
        purchaseDao.insertPurchase(purchase)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deletePurchase(purchase: Purchase) {
        purchaseDao.delPurchase(purchase)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updatePurchase(purchase: Purchase) {
        purchaseDao.updatePurchase(purchase)
    }
}