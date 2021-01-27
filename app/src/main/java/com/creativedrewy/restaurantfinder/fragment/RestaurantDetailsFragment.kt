package com.creativedrewy.restaurantfinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
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
                    //This feels kinda wrong to get the repeat loading animation to work. Probably a better way to do it.
                    viewBinding.detailsLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                        override fun onTransitionCompleted(motionLayout: MotionLayout?, p1: Int) {
                            motionLayout?.setTransition(R.id.start, R.id.end)
                            motionLayout?.transitionToEnd()
                        }

                        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) { }
                        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) { }
                        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) { }
                    })

                    viewBinding.detailsLayout.setTransition(R.id.start, R.id.end)
                    viewBinding.detailsLayout.transitionToEnd()
                }
                is DetailsError -> {
                    viewBinding.detailCryTexview.visibility = View.VISIBLE
                    viewBinding.detailErrorTexview.visibility = View.VISIBLE
                }
                is RestaurantLoaded -> {
                    with (viewBinding) {
                        storeNameTextview.text = it.details.displayName
                        storeDescTextview.text = it.details.desc
                        phoneTextview.text = it.details.phoneNumber
                        storeStatusTextview.text = it.details.status
                        deliveryFeeTexview.text = resources.getString(R.string.price_display, it.details.deliveryFee.toString())
                        ratingTextview.text = resources.getString(R.string.rating_display, it.details.rating.toString())

                        Glide.with(requireContext())
                            .load(it.details.imageUrl)
                            .centerCrop()
                            .into(headerImageview)

                        detailsLayout.setTransitionListener(null)
                        detailsLayout.setTransition(R.id.start, R.id.start)
                    }
                }
            }
        }

        viewModel.loadRestaurant(safeArgs.id)
    }
}