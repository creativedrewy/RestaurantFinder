package com.creativedrewy.restaurantfinder.api

import retrofit2.http.GET

interface RestaurantsEndpoints {

    @GET("")
    suspend fun loadRestaurantList(): RestaurantListDto

}