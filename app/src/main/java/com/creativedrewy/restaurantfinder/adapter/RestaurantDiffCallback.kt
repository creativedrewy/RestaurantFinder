package com.creativedrewy.restaurantfinder.adapter

import androidx.recyclerview.widget.DiffUtil
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetails

class RestaurantDiffCallback: DiffUtil.ItemCallback<RestaurantDetails>() {

    override fun areItemsTheSame(oldItem: RestaurantDetails, newItem: RestaurantDetails): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RestaurantDetails, newItem: RestaurantDetails): Boolean {
        return false
    }

}