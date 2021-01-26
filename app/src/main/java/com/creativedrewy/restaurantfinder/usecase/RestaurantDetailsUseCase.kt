package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.api.RestaurantInfoDto
import com.creativedrewy.restaurantfinder.repository.RestaurantsRepository
import javax.inject.Inject

class RestaurantDetailsUseCase @Inject constructor(
    private val repository: RestaurantsRepository
) {

    suspend fun getRestaurantDetails(id: Int): RestaurantInfoDto {
        return repository.loadRestaurantInfo(id)
    }

}