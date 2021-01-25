package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.api.RestaurantListDto
import com.creativedrewy.restaurantfinder.api.RestaurantsEndpoints
import javax.inject.Inject

class RestaurantsSourceUseCase @Inject constructor(
    private val endpoints: RestaurantsEndpoints
) {

    suspend fun listRestaurants(lat: Long, long: Long): RestaurantListDto {
        return endpoints.loadRestaurantList(lat, long, 0, 50)
    }

}