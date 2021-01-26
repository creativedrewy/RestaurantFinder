package com.creativedrewy.restaurantfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RestaurantDetailsViewModel @Inject constructor(): ViewModel() {

    val viewState: MutableLiveData<Boolean> = MutableLiveData()

    fun loadRestaurant(id: Int) {

    }

}