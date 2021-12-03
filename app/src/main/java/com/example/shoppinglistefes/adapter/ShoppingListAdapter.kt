package com.example.shoppinglistefes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglistefes.data.Purchase
import com.example.shoppinglistefes.databinding.ListItemBinding

class ShoppingListAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Purchase, ShoppingListVH>(itemComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ShoppingListVH(binding,listener)
    }

    override fun onBindViewHolder(holder: ShoppingListVH, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    companion object {
        val itemComparator = object : DiffUtil.ItemCallback<Purchase>() {
            override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}