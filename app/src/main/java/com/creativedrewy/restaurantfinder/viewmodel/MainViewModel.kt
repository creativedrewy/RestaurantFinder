package com.creativedrewy.restaurantfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(

) : ViewModel() {

    val viewState: MutableLiveData<TickerViewState> = MutableLiveData()

    fun loadStocks() {
        viewState.postValue(Loading)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    //Put this in here so it forces the UI to show my snazzy loading animation :)
                    delay(2000)

//                    val result =
//                    viewState.postValue()
                } catch (e: Exception) {
                    viewState.postValue(ErrorResult)
                }
            }
        }
    }

}