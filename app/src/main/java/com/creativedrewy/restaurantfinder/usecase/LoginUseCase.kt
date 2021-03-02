package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.repository.AuthTokenRepository
import com.creativedrewy.restaurantfinder.repository.RestaurantsRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val tokenRepository: AuthTokenRepository
) {

    fun checkLoginStatus(): Boolean {
        return tokenRepository.isTokenPresent()
    }

    suspend fun logIn(email: String, pass: String) {
        val result = repository.logIn(email, pass)
        tokenRepository.saveToken(result.token)
    }

}