package com.creativedrewy.restaurantfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.databinding.ItemRestaurantListBinding
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetails

class RestaurantListAdapter: ListAdapter<RestaurantDetails, RestaurantListAdapter.ViewHolder>(RestaurantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRestaurantListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemRestaurantListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantDetails) {
            //if (!restaurant.isLoading) {
            binding.itemMotionlayout.getTransition(R.id.loading_transition).autoTransition = MotionScene.Transition.AUTO_NONE
            binding.itemMotionlayout.getTransition(R.id.repeat_transition).autoTransition = MotionScene.Transition.AUTO_NONE
            binding.itemMotionlayout.transitionToState(R.id.fully_loaded)
            //}

            binding.titleTextview.text = restaurant.displayName

            if (!restaurant.isLoading) {
                binding.numberTextview.text = ""
            } else {
                binding.numberTextview.text = "           "
            }
        }
    }
}