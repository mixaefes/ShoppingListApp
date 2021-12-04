package com.example.shoppinglistefes.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistefes.data.Purchase
import com.example.shoppinglistefes.databinding.ListItemBinding

class ShoppingListVH(
    _binding: ListItemBinding
) : RecyclerView.ViewHolder(_binding.root){
    private val binding = _binding
    fun bind(item: Purchase) {
        binding.apply {
            mainTextView.text = item.name
            priceTextView.text = "${item.price?.toString()} ₪"
            dateTextView.text = item.data?.toString()
        }
        Log.i("ViewHolder", "myPurchase: $item")
    }

}