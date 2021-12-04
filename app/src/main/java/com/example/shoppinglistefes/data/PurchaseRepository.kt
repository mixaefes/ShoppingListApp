package com.example.shoppinglistefes.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PurchaseRepository(private val purchaseDao: Dao) {

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