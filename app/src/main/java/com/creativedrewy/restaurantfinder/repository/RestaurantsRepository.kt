package com.creativedrewy.restaurantfinder.repository

import com.creativedrewy.restaurantfinder.api.*
import javax.inject.Inject

/**
 * Class that knows how to consume the restaurant data source APIs
 */
class RestaurantsRepository @Inject constructor(
    private val endpoints: RestaurantsEndpoints
) {

    suspend fun logIn(email: String, pass: String): LoginResponseDto {
        val body = LoginRequestDto(email, pass)
        return endpoints.logInToSite(body)
    }

    suspend fun loadRestaurants(lat: Double, long: Double, offset: Int, limit: Int): RestaurantListDto {
        return endpoints.loadRestaurantList(lat, long, offset, limit)
    }

    suspend fun loadRestaurantInfo(id: Int): RestaurantInfoDto {
        return endpoints.loadRestaurantInfo(id)
    }

}