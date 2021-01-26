package com.creativedrewy.restaurantfinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.databinding.FragmentRestaurantDetailsBinding
import com.creativedrewy.restaurantfinder.viewmodel.DetailsError
import com.creativedrewy.restaurantfinder.viewmodel.DetailsLoading
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetailsViewModel
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantLoaded
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
            when (it) {
                is DetailsLoading -> {

                }
                is DetailsError -> {

                }
                is RestaurantLoaded -> {
                    with (viewBinding) {
                        storeNameTextview.text = it.details.displayName
                        storeDescTextview.text = it.details.desc
                        phoneTextview.text = it.details.phoneNumber
                        storeStatusTextview.text = it.details.status
                        deliveryFeeTexview.text = resources.getString(R.string.price_display, it.details.deliveryFee)
                        ratingTextview.text = resources.getString(R.string.rating_display, it.details.rating)

                        Glide.with(requireContext())
                            .load(it.details.imageUrl)
                            .centerCrop()
                            .into(headerImageview)
                    }
                }
            }
        }

        viewModel.loadRestaurant(safeArgs.id)
    }
}