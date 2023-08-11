package com.example.shoppinglistefes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglistefes.domain.Purchase
import com.example.shoppinglistefes.databinding.ListItemBinding
import java.util.*

class ShoppingListAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Purchase, ShoppingListVH>(itemComparator),
    ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ShoppingListVH(binding)
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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (element in fromPosition until toPosition) {
                Collections.swap(currentList.toMutableList(), element, element + 1)
            }
        } else {
            for (element in fromPosition downTo toPosition+1) {
                Collections.swap(currentList.toMutableList(), element, element - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        currentList.toMutableList().removeAt(position)
        notifyItemRemoved(position)
    }
}