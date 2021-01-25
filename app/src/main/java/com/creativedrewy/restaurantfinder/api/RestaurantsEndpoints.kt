package com.creativedrewy.restaurantfinder.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsEndpoints {

    @GET("v1/store_feed/")
    suspend fun loadRestaurantList(
        @Query("lat") lat: Long,
        @Query("lng") long: Long,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): RestaurantListDto

}