package com.example.shoppinglistefes.presentation.adapter

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
    fun onItemDismiss(position: Int)
}