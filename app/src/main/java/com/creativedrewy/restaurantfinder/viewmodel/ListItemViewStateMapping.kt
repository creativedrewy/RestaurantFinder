package com.creativedrewy.restaurantfinder.viewmodel

import android.content.res.Resources
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.api.RestaurantDto
import com.creativedrewy.restaurantfinder.api.RestaurantStatusDto
import javax.inject.Inject

class ListItemViewStateMapping @Inject constructor(
    private val resources: Resources
) {

    fun mapDtoToViewState(dto: RestaurantDto, loading: Boolean = false): RestaurantDetails {
        return RestaurantDetails(
            id = dto.id,
            isLoading = loading,
            displayName = dto.name,
            desc = dto.description,
            imageUrl = dto.coverImgUrl,
            status = getStatusString(dto.status)
        )
    }

    /**
     * I couldn't find any api results that had a non-null "closed reason" status value,
     * so I am just making a choice that non-null will mean a restaurant is closed.
     *
     * Also the "mins" concatenation isn't localization-friendly at all but is implemented
     * so I can drop some simple unit tests. A better implementation would be some sort of string-
     * providing abstraction.
     */
    private fun getStatusString(statusDto: RestaurantStatusDto): String {
        return when {
            statusDto.unavailableReason != null -> resources.getString(R.string.store_closed)
            statusDto.asapMinutesRange.size == 1 -> "${ statusDto.asapMinutesRange[0] } ${ resources.getString(R.string.asap_minutes) }"
            statusDto.asapMinutesRange.size == 2 -> {
                val average = ((statusDto.asapMinutesRange[0] + statusDto.asapMinutesRange[1]).toDouble() / 2).toInt()
                "$average ${ resources.getString(R.string.asap_minutes) }"
            }
            else -> ""
        }
    }

}