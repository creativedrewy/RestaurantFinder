package com.creativedrewy.restaurantfinder.viewmodel

sealed class ListViewState

object Loading : ListViewState() {
    val loadingItems = listOf(
        RestaurantDetails(isLoading = true),
        RestaurantDetails(isLoading = true)
    )
}

object ErrorResult: ListViewState()

data class RestaurantList(
    val restaurants: List<RestaurantDetails>
): ListViewState()

data class RestaurantDetails(
    val id: Int = -1,
    val isLoading: Boolean = false,
    val phoneNumber: String = "",
    val deliveryFee: Double = 0.0,
    val rating: Double = 0.0,
    val displayName: String = "",
    val desc: String = "",
    val status: String = "",
    val imageUrl: String = ""
)