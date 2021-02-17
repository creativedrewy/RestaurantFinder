package com.creativedrewy.restaurantfinder.repository

import android.content.SharedPreferences
import javax.inject.Inject

class FavoriteRestaurantsRepository @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getFavoritedRestuarnts(): List<Int> {
        return listOf(62087)
    }
}