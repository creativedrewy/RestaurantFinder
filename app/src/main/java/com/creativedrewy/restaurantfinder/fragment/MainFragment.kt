package com.creativedrewy.restaurantfinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativedrewy.restaurantfinder.adapter.RestaurantListAdapter
import com.creativedrewy.restaurantfinder.databinding.MainFragmentBinding
import com.creativedrewy.restaurantfinder.viewmodel.ErrorResult
import com.creativedrewy.restaurantfinder.viewmodel.Loading
import com.creativedrewy.restaurantfinder.viewmodel.MainViewModel
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewBinding: MainFragmentBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy { RestaurantListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = MainFragmentBinding.inflate(inflater, container, false)

        viewBinding.restaurantListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.restaurantListRecyclerview.adapter = adapter

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is Loading -> {
                    adapter.submitList(it.loadingItems)
                }
                is RestaurantList -> {
                    adapter.submitList(it.restaurants)
                }
                is ErrorResult -> {
                    with (viewBinding) {
                        restaurantListRecyclerview.visibility = View.GONE
                        cryTextview.visibility = View.VISIBLE
                        sorryTextTextview.visibility = View.VISIBLE
                    }
                }
            }
        })

        viewModel.loadRestaurants()
    }

}