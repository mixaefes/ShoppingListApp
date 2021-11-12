package com.example.shoppinglistefes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglistefes.Constants

@Database(entities = [Purchase::class], version = 1, exportSchema = false)
abstract class PurchaseDatabase : RoomDatabase() {
    abstract fun PurchaseDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: PurchaseDatabase? = null
        fun getDatabase(context: Context): PurchaseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PurchaseDatabase::class.java,
                    Constants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}