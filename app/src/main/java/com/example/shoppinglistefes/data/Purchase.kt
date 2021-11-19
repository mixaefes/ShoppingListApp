package com.example.shoppinglistefes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Purchase(
    @PrimaryKey(autoGenerate = true) var id: Int? = 0,
    val name: String,
    val data: Long? = null,
    val price: Int? = null
)
