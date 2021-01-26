package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.RestaurantDetailsUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel for restaurant details screen
 */
class RestaurantDetailsViewModel @ViewModelInject constructor(
    private val detailsUseCase: RestaurantDetailsUseCase
): ViewModel() {

    val viewState: MutableLiveData<DetailsViewState> = MutableLiveData()

    fun loadRestaurant(id: Int) {
        viewState.postValue(DetailsLoading)

        viewModelScope.launch {
            try {
                val dto = detailsUseCase.getRestaurantDetails(id)

                //To save time I'm just going to do the view state mapping here
                viewState.postValue(RestaurantLoaded(
                    details = RestaurantDetails(
                        id = id,
                        phoneNumber = dto.phoneNumber,
                        deliveryFee = dto.deliveryFee,
                        rating = dto.averageRating,
                        displayName = dto.name,
                        desc = dto.description,
                        status = dto.status,
                        imageUrl = dto.coverImgUrl
                    )
                ))
            } catch (e: Exception) {
                viewState.postValue(DetailsError)
            }

        }
    }

}