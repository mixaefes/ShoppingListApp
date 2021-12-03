package com.example.shoppinglistefes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null,
    val data: Long? = null,
    val price: Int? = 0
)
