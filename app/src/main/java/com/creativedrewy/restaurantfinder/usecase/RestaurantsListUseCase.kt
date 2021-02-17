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

    private var cachedResults: RestaurantListDto = RestaurantListDto(0, 0, listOf())

    suspend fun listRestaurants(lat: Double, long: Double): RestaurantListDto {
        cachedResults = restaurantsRepository.loadRestaurants(lat, long, listOffset, loadLimit)
        return updateFavoritedRestaurants()
    }

    fun updateFavoritedRestaurants(): RestaurantListDto {
        val favorites = favoriteRestaurants.getFavoritedRestuarnts()

        val newStores = cachedResults.stores.map { restaurantDto ->
            restaurantDto.copy(
                isFavorite = favorites.contains(restaurantDto.id)
            )
        }

        return cachedResults.copy(
            stores = newStores
        )
    }

    fun toggleFavoriteRestaurant(id: Int) {
        favoriteRestaurants.toggleFavorites(id)
    }

}