package com.creativedrewy.restaurantfinder.api

data class RestaurantListDto(
    val numResults: Int,
    val nextOffset: Int,
    val stores: List<RestaurantDto>
)

data class RestaurantDto(
    val id: Int,
    val name: String,
    val description: String,
    val coverImgUrl: String,
    val isFavorite: Boolean,    //This didn't come from the API
    val status: RestaurantStatusDto
)

data class RestaurantStatusDto(
    val unavailableReason: String?,
    val asapMinutesRange: List<Int>
)

data class RestaurantInfoDto(
    val phoneNumber: String,
    val deliveryFee: Double,
    val averageRating: Double,
    val status: String,
    val description: String,
    val name: String,
    val coverImgUrl: String
)

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String
)