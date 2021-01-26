package com.creativedrewy.restaurantfinder.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsEndpoints {

    @GET("v1/store_feed/")
    suspend fun loadRestaurantList(
        @Query("lat") lat: Double,
        @Query("lng") long: Double,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): RestaurantListDto

    @GET("v2/restaurant/{id}/")
    suspend fun loadRestaurantInfo(@Path("id") id: Int): RestaurantInfoDto

}