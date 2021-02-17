package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.api.RestaurantListDto
import com.creativedrewy.restaurantfinder.repository.FavoriteRestaurantsRepository
import com.creativedrewy.restaurantfinder.repository.RestaurantsRepository
import javax.inject.Inject

/**
 * Business logic around primary restaurant listing
 */
class RestaurantsListUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    private val favoriteRestaurants: FavoriteRestaurantsRepository
) {

    private var listOffset: Int = 0
    private var loadLimit: Int = 50

    suspend fun listRestaurants(lat: Double, long: Double): RestaurantListDto {
        val apiResults = restaurantsRepository.loadRestaurants(lat, long, listOffset, loadLimit)
        val favorites = favoriteRestaurants.getFavoritedRestuarnts()

        val newStores = apiResults.stores.map { restaurantDto ->
            restaurantDto.copy(
                isFavorite = favorites.contains(restaurantDto.id)
            )
        }

        return apiResults.copy(
            stores = newStores
        )
    }

    fun toggleFavoriteRestaurant(id: Int) {
        favoriteRestaurants.toggleFavorites(id)
    }

}