package com.creativedrewy.restaurantfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.databinding.ItemStockListBinding
import com.creativedrewy.restaurantfinder.viewmodel.StockDetails

class StockListAdapter: ListAdapter<StockDetails, StockListAdapter.ViewHolder>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStockListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemStockListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: StockDetails) {
            if (!stock.isLoading) {
                binding.itemMotionlayout.getTransition(R.id.loading_transition).autoTransition = MotionScene.Transition.AUTO_NONE
                binding.itemMotionlayout.getTransition(R.id.repeat_transition).autoTransition = MotionScene.Transition.AUTO_NONE
                binding.itemMotionlayout.transitionToState(R.id.fully_loaded)
            }

            binding.tickerTextview.text = stock.ticker
            binding.titleTextview.text = stock.displayName
            binding.priceTextview.text = stock.displayPrice

            if (!stock.isLoading) {
                binding.quantityTextview.text = "(${stock.quantity})"
            } else {
                binding.quantityTextview.text = "           "
            }
        }
    }
}