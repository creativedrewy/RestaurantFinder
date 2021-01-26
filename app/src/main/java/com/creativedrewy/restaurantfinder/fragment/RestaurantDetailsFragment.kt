package com.creativedrewy.restaurantfinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.creativedrewy.restaurantfinder.databinding.FragmentRestaurantDetailsBinding
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment() {

    private val safeArgs: RestaurantDetailsFragmentArgs by navArgs()
    private val viewModel: RestaurantDetailsViewModel by viewModels()

    private lateinit var viewBinding: FragmentRestaurantDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentRestaurantDetailsBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) {

        }

        viewModel.loadRestaurant(safeArgs.id)
    }
}