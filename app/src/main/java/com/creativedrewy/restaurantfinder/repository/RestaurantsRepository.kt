package com.creativedrewy.restaurantfinder.repository

import com.creativedrewy.restaurantfinder.api.RestaurantInfoDto
import com.creativedrewy.restaurantfinder.api.RestaurantListDto
import com.creativedrewy.restaurantfinder.api.RestaurantsEndpoints
import javax.inject.Inject

/**
 * Class that knows how to consume the restaurant data source APIs
 */
class RestaurantsRepository @Inject constructor(
    private val endpoints: RestaurantsEndpoints
) {

    suspend fun loadRestaurants(lat: Double, long: Double, offset: Int, limit: Int): RestaurantListDto {
        return endpoints.loadRestaurantList(lat, long, offset, limit)
    }

    suspend fun loadRestaurantInfo(id: Int): RestaurantInfoDto {
        return endpoints.loadRestaurantInfo(id)
    }

}