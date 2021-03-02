package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    fun loginToDoorDash(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginUseCase.logIn(email, password)
            }

        }
    }

}