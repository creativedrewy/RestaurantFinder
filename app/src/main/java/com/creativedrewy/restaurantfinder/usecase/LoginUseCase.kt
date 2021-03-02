package com.creativedrewy.restaurantfinder.usecase

import android.util.Log
import com.creativedrewy.restaurantfinder.repository.RestaurantsRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val repository: RestaurantsRepository
) {

    suspend fun logIn(email: String, pass: String) {
        val result = repository.logIn(email, pass)

        Log.v("Andrew", "Your Token: ${ result.token }")
    }

}