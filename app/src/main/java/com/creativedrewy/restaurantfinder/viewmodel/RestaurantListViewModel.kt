package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.api.RestaurantListDto
import com.creativedrewy.restaurantfinder.usecase.RestaurantsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for restaurant list screen
 */
class RestaurantListViewModel @ViewModelInject constructor(
    private val restaurantsUseCase: RestaurantsListUseCase,
    private val viewStateMapping: ListItemViewStateMapping
) : ViewModel() {

    val viewState: MutableLiveData<ListViewState> = MutableLiveData()

    fun loadRestaurants(latitude: Double, longitude: Double) {
        viewState.postValue(Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = restaurantsUseCase.listRestaurants(latitude, longitude)
                    postLatestRestaurants(result)
                } catch (e: Exception) {
                    viewState.postValue(ErrorResult)
                }
            }
        }
    }

    fun toggleFavorite(id: Int) {
        restaurantsUseCase.toggleFavoriteRestaurant(id)
        postLatestRestaurants(restaurantsUseCase.updateFavoritedRestaurants())
    }

    private fun postLatestRestaurants(dto: RestaurantListDto) {
        viewState.postValue(RestaurantList(
            restaurants = dto.stores.map(viewStateMapping::mapDtoToViewState)
        ))
    }
}