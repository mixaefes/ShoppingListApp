package com.example.shoppinglistefes.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String? = null,
    val data: Long? = null,
    var price: Int? = 0
)
