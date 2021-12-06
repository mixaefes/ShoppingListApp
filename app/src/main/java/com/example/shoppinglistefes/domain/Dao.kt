package com.example.shoppinglistefes.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM purchase WHERE price == 0")
    fun getAll(): Flow<List<Purchase>>

    @Query("SELECT * FROM purchase WHERE price > 0")
    fun getAllWasted():Flow<List<Purchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: Purchase)

    @Delete
    suspend fun delPurchase(purchase: Purchase)

    @Update
    suspend fun updatePurchase(purchase: Purchase)
}