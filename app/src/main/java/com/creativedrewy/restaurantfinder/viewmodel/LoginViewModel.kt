package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativedrewy.restaurantfinder.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class LoginViewState

object AlreadyLoggedIn: LoginViewState()

object LoginSuccess: LoginViewState()

object LoginFailure: LoginViewState()

class LoginViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    val viewState: MutableLiveData<LoginViewState> = MutableLiveData()

    fun loginToDoorDash(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loginUseCase.logIn(email, password)
                    viewState.postValue(LoginSuccess)
                } catch (e: Exception) {
                    viewState.postValue(LoginFailure)
                }
            }
        }
    }

    fun checkIsLoggedIn(): Boolean {
        return loginUseCase.checkLoginStatus()
    }
}