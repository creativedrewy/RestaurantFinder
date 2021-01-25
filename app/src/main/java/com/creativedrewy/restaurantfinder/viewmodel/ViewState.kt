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
    val isLoading: Boolean = false,
    val displayName: String = "",
    val desc: String = "",
    val status: String = "",
    val imageUrl: String = ""
)