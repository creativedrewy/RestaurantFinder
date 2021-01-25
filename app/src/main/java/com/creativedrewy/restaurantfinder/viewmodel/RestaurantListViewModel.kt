package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.RestaurantsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantListViewModel @ViewModelInject constructor(
    private val restaurantsUseCase: RestaurantsListUseCase
) : ViewModel() {

    val viewState: MutableLiveData<ListViewState> = MutableLiveData()

    fun loadRestaurants() {
        viewState.postValue(Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    //Put this in here so it forces the UI to show my snazzy loading animation :)
                    //delay(2000)

                    val result = restaurantsUseCase.listRestaurants(37.422740.toLong(), -122.139956.toLong())
                    viewState.postValue(RestaurantList(
                        restaurants = result.stores.map {
                            RestaurantDetails(isLoading = false, displayName = it.name)
                        }
                    ))
                } catch (e: Exception) {
                    viewState.postValue(ErrorResult)
                }
            }
        }
    }

}