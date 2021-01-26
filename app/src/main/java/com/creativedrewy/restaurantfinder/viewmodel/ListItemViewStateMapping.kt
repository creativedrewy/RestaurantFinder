package com.creativedrewy.restaurantfinder.viewmodel

import android.content.res.Resources
import com.creativedrewy.restaurantfinder.api.RestaurantDto
import javax.inject.Inject

class ListItemViewStateMapping @Inject constructor(
    private val resources: Resources
) {

    fun mapDtoToViewState(dto: RestaurantDto): RestaurantDetails {

//        RestaurantDetails(
//            isLoading = false,
//            displayName = it.name,
//            desc = it.description,
//            status = "10 mins",
//            imageUrl = it.coverImgUrl
//        )


        return RestaurantDetails()
    }

}