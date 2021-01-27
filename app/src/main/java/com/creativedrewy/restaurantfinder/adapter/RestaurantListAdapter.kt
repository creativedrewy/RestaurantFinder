package com.creativedrewy.restaurantfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.databinding.ItemRestaurantListBinding
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetails

class RestaurantListAdapter: ListAdapter<RestaurantDetails, RestaurantListAdapter.ViewHolder>(RestaurantDiffCallback()) {

    var rowClickListener: (Int) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRestaurantListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), rowClickListener)
    }

    class ViewHolder(private val binding: ItemRestaurantListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantDetails, onClick: (Int) -> Unit = { _ -> }) {
            if (!restaurant.isLoading) {
                binding.itemMotionlayout.getTransition(R.id.loading_transition).autoTransition = MotionScene.Transition.AUTO_JUMP_TO_START
                binding.itemMotionlayout.getTransition(R.id.reset_transition).autoTransition = MotionScene.Transition.AUTO_JUMP_TO_START
                binding.itemMotionlayout.setTransition(R.id.start, R.id.start)

                binding.restaurantImageview.alpha = 1.0f

                binding.root.setOnClickListener { onClick(restaurant.id) }
            } else {
                binding.restaurantImageview.alpha = 0.4f
            }

            Glide.with(binding.root)
                .load(restaurant.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder_24)
                .into(binding.restaurantImageview)

            binding.nameTextview.text = restaurant.displayName
            binding.descTextview.text = restaurant.desc
            binding.statusTextview.text = restaurant.status
        }
    }
}