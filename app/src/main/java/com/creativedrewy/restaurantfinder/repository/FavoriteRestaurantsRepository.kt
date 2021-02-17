package com.creativedrewy.restaurantfinder.repository

import android.content.SharedPreferences
import java.lang.Integer.parseInt
import javax.inject.Inject

class FavoriteRestaurantsRepository @Inject constructor(
    private val prefs: SharedPreferences
) {

    companion object {
        const val SAVED_RESTAURANTS = "favorites"
    }

    fun getFavoritedRestuarnts(): List<Int> {
        val sourceIds = prefs.getString(SAVED_RESTAURANTS, "")
        val ids = sourceIds?.split(",") ?: listOf()

        return ids.map {
            if (it.isNotEmpty()) {
                parseInt(it)
            } else {
                0
            }
        }
    }

    fun toggleFavorites(restId: Int) {
        val sourceSaved = prefs.getString(SAVED_RESTAURANTS, "")

        val ids = sourceSaved?.split(",")
        ids?.toMutableList()?.let { mutableIds ->
            if (mutableIds.contains(restId.toString())) {
                mutableIds.remove(restId.toString())
            } else {
                mutableIds.add(restId.toString())
            }

            val concatString = mutableIds.joinToString(",")
            prefs.edit().putString(SAVED_RESTAURANTS, concatString).commit()
        }
    }
}