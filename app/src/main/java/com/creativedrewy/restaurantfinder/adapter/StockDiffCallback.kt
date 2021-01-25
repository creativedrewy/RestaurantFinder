package com.creativedrewy.restaurantfinder.adapter

import androidx.recyclerview.widget.DiffUtil
import com.creativedrewy.restaurantfinder.viewmodel.StockDetails

class StockDiffCallback: DiffUtil.ItemCallback<StockDetails>() {

    override fun areItemsTheSame(oldItem: StockDetails, newItem: StockDetails): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StockDetails, newItem: StockDetails): Boolean {
        return oldItem.displayName == newItem.displayName &&
                oldItem.displayPrice == newItem.displayPrice &&
                oldItem.quantity == newItem.quantity &&
                oldItem.ticker == newItem.ticker
    }

}