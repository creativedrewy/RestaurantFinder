package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(

) : ViewModel() {

    val viewState: MutableLiveData<ListViewState> = MutableLiveData()

    fun loadRestaurants() {
        viewState.postValue(Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    //Put this in here so it forces the UI to show my snazzy loading animation :)
                    //delay(2000)

//                    val result =
                    viewState.postValue(RestaurantList(
                        restaurants = listOf(
                            RestaurantDetails(isLoading = false, displayName = "Test"),
                            RestaurantDetails(isLoading = false, displayName = "Test 2"),
                            RestaurantDetails(isLoading = false, displayName = "Test")
                        )
                    ))
                } catch (e: Exception) {
                    viewState.postValue(ErrorResult)
                }
            }
        }
    }

}