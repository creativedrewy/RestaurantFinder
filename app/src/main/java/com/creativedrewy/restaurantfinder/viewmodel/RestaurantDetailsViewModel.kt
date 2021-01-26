package com.creativedrewy.restaurantfinder.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.RestaurantDetailsUseCase
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel @ViewModelInject constructor(
    private val detailsUseCase: RestaurantDetailsUseCase
): ViewModel() {

    val viewState: MutableLiveData<Boolean> = MutableLiveData()

    fun loadRestaurant(id: Int) {
        viewModelScope.launch {
            val infoDto = detailsUseCase.getRestaurantDetails(id)
            Log.v("Andrew", "Your DTO $infoDto")
        }
    }

}