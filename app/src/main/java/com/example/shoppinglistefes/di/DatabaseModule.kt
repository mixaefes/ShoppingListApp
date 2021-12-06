package com.example.shoppinglistefes.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglistefes.Constants
import com.example.shoppinglistefes.data.PurchaseDatabase
import com.example.shoppinglistefes.domain.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providePurchaseDao(purchaseDatabase: PurchaseDatabase):Dao{
        return purchaseDatabase.purchaseDao()
    }
    @Provides
    @Singleton
    fun providePurchaseDatabase(@ApplicationContext appContext: Context):PurchaseDatabase{
        return Room.databaseBuilder(
            appContext,
            PurchaseDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }
}