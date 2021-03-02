package com.creativedrewy.restaurantfinder.repository

import android.content.SharedPreferences
import javax.inject.Inject

class AuthTokenRepository @Inject constructor(
    private val prefs: SharedPreferences
) {

    companion object {
        const val CACHED_TOKEN = "authToken"
    }

    fun saveToken(token: String) {
        prefs.edit().putString(CACHED_TOKEN, token).apply()
    }

    fun isTokenPresent(): Boolean {
        return prefs.getBoolean(CACHED_TOKEN, false)
    }
}