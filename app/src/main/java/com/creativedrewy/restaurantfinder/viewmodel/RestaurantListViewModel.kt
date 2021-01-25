package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.RestaurantsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantListViewModel @ViewModelInject constructor(
    private val restaurantsUseCase: RestaurantsListUseCase
) : ViewModel() {

    val viewState: MutableLiveData<ListViewState> = MutableLiveData()

    fun loadRestaurants(latitude: Double, longitude: Double) {
        viewState.postValue(Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    //Put this in here so it forces the UI to show my snazzy loading animation :)
                    delay(1000)

                    val result = restaurantsUseCase.listRestaurants(latitude, longitude)
                    viewState.postValue(RestaurantList(
                        restaurants = result.stores.map {
                            RestaurantDetails(
                                isLoading = false,
                                displayName = it.name,
                                desc = it.description,
                                status = "10 mins",
                                imageUrl = it.coverImgUrl
                            )
                        }
                    ))
                } catch (e: Exception) {
                    viewState.postValue(ErrorResult)
                }
            }
        }
    }

}