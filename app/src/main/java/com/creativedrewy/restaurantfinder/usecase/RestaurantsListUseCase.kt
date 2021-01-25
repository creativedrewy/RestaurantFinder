package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.api.RestaurantListDto
import com.creativedrewy.restaurantfinder.repository.RestaurantsRepository
import javax.inject.Inject

/**
 * Business logic around primary restaurant listing
 */
class RestaurantsListUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) {

    private var listOffset: Int = 0
    private var loadLimit: Int = 50

    suspend fun listRestaurants(lat: Double, long: Double): RestaurantListDto {
        return restaurantsRepository.loadRestaurants(lat, long, listOffset, loadLimit)
    }

}