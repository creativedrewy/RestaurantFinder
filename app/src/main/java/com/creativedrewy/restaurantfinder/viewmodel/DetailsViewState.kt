package com.creativedrewy.restaurantfinder.viewmodel

sealed class DetailsViewState

object DetailsLoading: DetailsViewState()

object DetailsError: DetailsViewState()

data class RestaurantLoaded(
    val details: RestaurantDetails  //We are reusing this object from the other view state
): DetailsViewState()